package com.qcby.jedis;

/**
 * @author: jangyu.zhao
 * @date: 2022/8/16
 **/

public class BoolmTest {
    public static void main(String[] args) {
        String value1="dasauda";
        String value2="sdafeedfe";
        MyBoolmFilter filter=new MyBoolmFilter();
        filter.add(value1);
        filter.add(value2);
        System.out.println(filter.contains(value1));
    }
}
