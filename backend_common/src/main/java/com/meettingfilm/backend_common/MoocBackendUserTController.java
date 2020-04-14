package com.meettingfilm.backend_common;


import com.meettingfilm.backend.utils.MD5Util;
import com.meettingfilm.backend_common.dao.mapper.MoocBackendUserTMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author chuck
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/moocBackendUserT")
public class MoocBackendUserTController {

    @Autowired
    private MoocBackendUserTMapper moocBackendUserTMapper;

    @ApiOperation(value = "测试swagger", notes = "测试swagger")
    @GetMapping("/test")
    public ResponseEntity sendByForgetPwd(@ApiParam(name = "tel", value = "手机号", required = true)
                                              @RequestParam(value = "tel") String tel) {System.out.print(moocBackendUserTMapper);

        return new ResponseEntity(true) ;

    }



}
