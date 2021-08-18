package com.atguigu.test;

import java.math.BigDecimal;

/**
 * @author Chunsheng Zhang 尚硅谷
 * @create 2021/8/18 9:11
 */
public class TestBigDecimal {


    public static void main(String[] args) {

        double d = 0.33,d2=0.33;
        System.out.println(d*d2);

        BigDecimal b = new BigDecimal("0.33");
        BigDecimal b2 = new BigDecimal("0.33");

//        b.subtract()  b.add()
//        b.multiply()  b.divide()

        BigDecimal rs = b.multiply(b2);
        double v = rs.doubleValue();
        System.out.println("v = " + v);

    }

}
