package com.meettingfilm.backend.pojo_generator;

import com.ilinklink.dao.mapper.PtCommonParamMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class PojoGeneratorApplicationTests {

    @Resource
    private PtCommonParamMapper ptCommonParamMapper;

    @Test
    void contextLoads() {
        log.error("error");
        log.warn("ptCommonParamMapper{}",ptCommonParamMapper);

    }


}
