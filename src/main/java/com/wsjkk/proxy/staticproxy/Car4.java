package com.wsjkk.proxy.staticproxy;

/**
 * 使用聚合的方式实现
 */
public class Car4 implements Moveable{
    private Car2 car2;

    public Car4(Car2 car2) {
        this.car2 = car2;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车行驶开始时间");
        car2.move();
        Long endTime = System.currentTimeMillis();
        System.out.println("汽车结束行驶。。。 汽车行驶时间：" + (endTime - startTime) + "毫秒");

    }
}
