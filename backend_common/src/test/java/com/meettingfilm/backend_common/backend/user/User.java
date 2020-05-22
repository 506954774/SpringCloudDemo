package com.meettingfilm.backend_common.backend.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meettingfilm.backend_common.dao.entity.MoocBackendUserT;
import com.meettingfilm.backend_common.dao.mapper.MoocBackendUserTMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by ilinklink on 2020/3/14.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class User {

    @Autowired
    private MoocBackendUserTMapper moocBackendUserTMapper;

    @Test
    public void add(){

        System.out.print(moocBackendUserTMapper);

        for (int i = 0; i < 5; i++) {
            MoocBackendUserT user=new MoocBackendUserT();
            user.setUserName("admin"+i);
            user.setUserPhone("" + 13000000000L+i);
            user.setUuid(i+1);
            user.setUserPwd("123456");
            moocBackendUserTMapper.insert(user);


        }


        QueryWrapper<MoocBackendUserT> wrapper=new QueryWrapper<>();
        wrapper.eq("","");
        moocBackendUserTMapper.selectOne(wrapper);
    }
}
