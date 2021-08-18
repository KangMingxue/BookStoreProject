package com.atguigu.test;

import com.atguigu.utils.MD5Util;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/11 16:07
 */
public class TestMD5 {

    public static void main(String[] args) {

        String pwd = "123456";

        String encode = MD5Util.encode(pwd);

        System.out.println("encode = " + encode);

    }

}
