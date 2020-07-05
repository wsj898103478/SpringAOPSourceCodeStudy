package com.wsjkk.proxy.staticproxy;

/**
 * 继承方式实现
 */
public class Car3 extends Car2{
    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车行驶开始时间");
        super.move();
        Long endTime = System.currentTimeMillis();
        System.out.println("汽车结束行驶。。。 汽车行驶时间：" + (endTime - startTime) + "毫秒");

    }
}
