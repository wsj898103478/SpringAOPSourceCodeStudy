package com.wsjkk.proxy.staticproxy;

/**
 * 使用聚合的方式实现
 */
public class CarTimeProxy implements Moveable{
    private Moveable moveable;

    public CarTimeProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车行驶开始时间");
        moveable.move();
        Long endTime = System.currentTimeMillis();
        System.out.println("汽车结束行驶。。。 汽车行驶时间：" + (endTime - startTime) + "毫秒");

    }
}
