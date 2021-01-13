package com.meettingfilm.backend.backend_gateway;

/**
 * Test
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/6  11:31
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public class Test {

    public static void main(String[] args) {

        StringBuilder a=new StringBuilder("A");
        StringBuilder b=new StringBuilder("B");

        new IdeaLicenseTest.A().exchange(a,b);
        System.out.println(a.toString());
        System.out.println(b.toString());
    }
}
