package com.qcby.jedis;

import java.util.BitSet;

/**
 * @author: jangyu.zhao
 * @date: 2022/8/16
 **/

public class MyBoolmFilter {
    //设置位数组的大小
    private static final int DEFAULT_SIZE=2<<10;

    //可以通过这个数组创建六个不同的hash函数
    private static final int[] seeds=new int[]{10,45,234,65,42,78};

    //位数组，数组中只能存储0-1
    private BitSet bits=new BitSet(DEFAULT_SIZE);

    //存放hash函数的类的数组
    private SimpleHash[] func=new SimpleHash[seeds.length];

    //初始化多个包含hash函数的累的数组，每个hash函数都不一样
    public MyBoolmFilter() {
        for (int i = 0; i < seeds.length; i++) {
            //初始化多个不同的hash函数
            func[i]=new SimpleHash(DEFAULT_SIZE,seeds[i]);
        }
    }

    //添加元素到位数组
    public void add(Object value){
        for (SimpleHash f : func) {
            bits.set(f.hash(value),true);
        }
    }

    //判断元素是否在
    public boolean contains(Object val){
        boolean rst=true;
        for (SimpleHash f : func) {
            rst=rst& bits.get(f.hash(val));
        }
        return rst;
    }




}

/**
 * 内部类，hash操作
 */
class SimpleHash{
    private int cap;
    private int seed;

    public SimpleHash(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }
    /**
     * 计算hash值
     */
    public int hash(Object value){
        int h;
        return (value==null)?0:Math.abs(seed*(cap-1)&(h=value.hashCode())^(h>>>16));
    }
}
