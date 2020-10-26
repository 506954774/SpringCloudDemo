package com.meettingfilm.backend_test_provider;

import com.meettingfilm.backend.utils.JwtTokenUtil;
import com.meettingfilm.backend.utils.auth.AccessToken;
import com.meettingfilm.backend_common.ResponseEntity;
import com.meettingfilm.backend_test_provider.upload.FastDFSClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.JwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ilinklink on 2020/3/18.
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends ServerExceptionHandler {

    private static final String LOGGER_PREFIX = "生产者服务";
    @Value("${server.port}")
    private int port;

    @Value("${dfs.max.size}")
    private int maxSize;

    @Value("${dfs.client.host}")
    private String dfsHost;

    @Autowired
    private FastDFSClient fastDFSClient;

    /*
    fallback是业务处理的保底方案，尽可能保证这个保底方案一定能成功
 */
    public ResponseEntity fallbackMethod( String message) throws AdminException{
        /*
            打发票， -》 没打印纸了， 换台机器或者下次再试
            -》 触发告警 -》 告知运维人员，打印发票业务挂了
         */
        // describeCinemas -》 获取影院列表 -> 在Redis中查询影院列表[redis挂了，或者数据没了] -> 获取超时

        // fallback里捕获到超时或者数据内容与分页数不一致

        // fallback就在数据库中查询真实的影院信息

        // 返回一定是成功，或者业务处理失败
        ResponseEntity <String> result=new ResponseEntity(true);
        result.setResult("fallbackMethod！ provider:"+port+","+message);
        return result ;
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value= "1000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1"),
                    @HystrixProperty(name = "maxQueueSize", value = "10"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "1000"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "8"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1500")
            } ,ignoreExceptions = AdminException.class)
    //,ignoreExceptions = AdminException.class
    @ApiOperation(value = "测试eureka调用", notes = "测试eureka调用")
    @GetMapping("/getMessage")
    public ResponseEntity testProvider(@ApiParam(name = "message", value = "信息", required = true)
                                          @RequestParam(value = "message") String message) throws AdminException {

        if(message.startsWith("error")){
            throw  new AdminException("error","数据不合法，业务异常！");
        }
        else if(message.startsWith("sleep")){
            // 服务降级:模拟超时导致降级
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {

            }
        }

        ResponseEntity <String> result=new ResponseEntity(true);
        result.setResult("this is from provider:"+port+","+message);
        return result ;

    }


    @ApiOperation(value="测试post请求", response = Void.class, notes="测试post请求")
    @PostMapping("/auth.do")
    public ResponseEntity auth(@RequestBody AccessToken params) {

        log.info("生产者接收到的参数:" + params);

        JwtTokenUtil util=new JwtTokenUtil();

        if(params==null){

            params=new AccessToken();
            params.setToken("参数为空！");
            params.setRandomKey("null");
            params.setPort(port);
            ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(params);
            return responseEntity;
        }

        try {
            util.parseToken(params.getToken());
        } catch (JwtException e) {
            params=new AccessToken();
            params.setToken("不合法的token");
            params.setRandomKey("null");
            params.setPort(port);

            ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(params);
            return responseEntity;
        }

        try {
            Date expirationDateFromToken = util.getExpirationDateFromToken(params.getToken());

            SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            params.setUserId(util.getuserIdfromtoken(params.getToken()));
            params.setToken("token 过期时间："+format.format(expirationDateFromToken));
            params.setRandomKey("null");
            params.setPort(port);

            ResponseEntity<AccessToken> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(params);
            return responseEntity;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }


    }

    @ApiOperation(value = "单文件上传", response = String.class, notes = "单文件上传,返回可访问的路径")
    @PostMapping("/upload")
    public ResponseEntity uploadFile(HttpServletRequest request) {
        try {
            String result= uploadFile2(request);
            ResponseEntity<String> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    @ApiOperation(value = "多文件上传",  response = ArrayList.class, notes = "文件批量上传,返回list<String>")
    @PostMapping("/multi_upload")
    public ResponseEntity multiImport(HttpServletRequest request) {
        try {
            List<String> result= multiImport2(request);
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            responseEntity.setResult(result);
            return responseEntity;
        } catch (AdminException e) {
            return new ResponseEntity<>(e.getErrorCode(), false, e.getMessage());
        }
    }

    public String uploadFile2(HttpServletRequest request) throws AdminException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile mFile = multipartRequest.getFile("file");
        if(mFile==null){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException("","上传内容为空!");
        }
        else {
            try {
                return  uploadFile(mFile);
            } catch (AdminException e) {
                throw e;
            }
        }
    }

    public List<String> multiImport2(HttpServletRequest request) throws AdminException {

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if(files==null||files.size()==0){
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException("上传内容为空!");
        }
        else {
            List<String> result = new ArrayList<>();
            ResponseEntity<List<String>> responseEntity = new ResponseEntity<>(true);
            for (MultipartFile singleFile:files) {
                try {
                    String url = uploadFile(singleFile);
                    result.add(url);
                } catch (AdminException e) {
                    throw e;
                }
            }
            return result;
        }
    }


    public String uploadFile(MultipartFile mFile) throws AdminException {
        if (mFile == null) {
            log.warn(LOGGER_PREFIX+"上传内容为空!", "");
            throw new AdminException("","上传内容为空!");
        }
        if (mFile.getSize() > maxSize * 1024 * 1024) {
            log.warn( "[文件上传]文件内容过大，上传失败！文件大小[" + mFile.getSize() + "].");
            throw new AdminException("","[文件上传]文件内容过大，上传失败！");
        }

        // 获取后缀名
        String originalFileName = mFile.getOriginalFilename();
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileType.equalsIgnoreCase("jpeg") && !fileType.equalsIgnoreCase("jpg") && !fileType.equalsIgnoreCase("png")) {
            log.warn(  "[文件上传]文件类型不合法，上传失败！文件类型[" + fileType + "].");
            throw new AdminException("","[文件上传]文件类型不合法，上传失败!");
        }

        try {
            InputStream inputStream = mFile.getInputStream();
            byte[] file_buff = null;
            if (inputStream != null) {
                int len = inputStream.available();
                file_buff = new byte[len];
                inputStream.read(file_buff);
            }

            String fileUrl = fastDFSClient.uploadFile(file_buff, fileType);
            log.info( "[文件上传]文件上传成功！文件链接url[" + fileUrl + "].");
            return dfsHost + "/" + fileUrl;
        } catch (Exception e) {
            log.error( "[文件上传]文件上传失败！", e);
            throw new AdminException("","[文件上传]文件上传失败！");
        }
    }


}
