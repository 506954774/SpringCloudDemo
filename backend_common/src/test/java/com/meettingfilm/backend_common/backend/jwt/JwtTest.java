package com.meettingfilm.backend_common.backend.jwt;

import com.meettingfilm.backend.utils.JwtTokenUtil;
import com.meettingfilm.backend_common.dao.entity.MoocBackendUserT;
import com.meettingfilm.backend_common.dao.mapper.MoocBackendUserTMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by ilinklink on 2020/3/17.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class JwtTest {


    @Test
    public void generate(){
        JwtTokenUtil util=new JwtTokenUtil();

        String name="admin";
        String randomKey=util.getRandomKey();

        log.info("name:"+name);
        log.info("randomKey:" + randomKey);

        String token=util.generateToken(name, randomKey);
        log.info("token:" + token);

        String username=util.getUsernameFromToken(token);
        log.info("username:" + username);

        Date time=util.getExpirationDateFromToken(token);

        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info("过期时间:" + format.format(time));


/*        boolean expired= util.isTokenExpired("avb");
        log.info("token是否失效:"+expired);*/


    }

    @Test
    public void check(){

    }
}
