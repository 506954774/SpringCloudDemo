package com.meettingfilm.backend.backend_gateway;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * IdeaLicenseTest
 * 责任人:  Chuck
 * 修改人： Chuck
 * 创建/修改时间: 2020/12/5  10:28
 * Copyright : 2014-2018 深圳令令科技有限公司-版权所有
 **/
public interface IdeaLicenseTest {

   static void main1(String[] args) {

        //System.out.printf("jiekou");

       ArrayList<Integer> list=new ArrayList<>();
       list.add(7);
       list.add(4);
       list.add(2);
       list.add(1);
       list.add(5);
       list.add(3);
       list.add(6);
       list.add(0);
       Integer[] array= new Integer[list.size()];
       for (int i = 0; i < list.size(); i++) {
           array[i]=list.get(i);
       }

       for (int i = 0; i < array.length-1; i++) {
           int a=array[i];
           int b=array[i+1];
           if(a>=b){
               array[i]=b;
               array[i+1]=a;
           }
           for (int j = 0; j <array.length-1; j++) {
               int a1=array[j];
               int b1=array[j+1];
               if(a1>=b1){
                   array[j]=b1;
                   array[j+1]=a1;
               }
           }
       }
       System.out.printf(Arrays.toString(array));
    }

    public static void main2(String[] args) {
        int x=4;
        System.out.printf("value is "+((x>4)?99.9:100));
        //输出：value is 100.0
    }

    public static void main(String[] args) {

       StringBuilder a=new StringBuilder("A");
       StringBuilder b=new StringBuilder("B");

       new A().exchange(a,b);
        System.out.println(a.toString());
        System.out.println(b.toString());
    }

    class A {
        void exchange(StringBuilder a,StringBuilder b){
              a.append("-more");
              b=new StringBuilder("new");
        }

       public  float test(float a,float b)throws Exception{
           return 0;
       }
       public  float testThrow(float a,float b)throws Exception{
           return 0;
       }
       public  float noThrow(float a,float b) {
           return 0;
       }
    }
    class B extends A{
       //重写，声明符号允许更宽松，不可更保密。父类是public则子类必须也public,否则编译不过
        float test1(float a,float b)throws Exception{
            return 0;
        }
        //父类方法throws异常，子类重写可以不用抛（更宽松）
        public float testThrow(float a,float b) {
            return 0;
        }
        //父类没抛异常，则子类也不能抛,否则编译不过
        public  float noThrow1(float a,float b)throws Exception {
            return 0;
        }
    }
}
