package com.wsjkk.proxy.staticproxy;

import java.util.Random;

public class Car implements Moveable{
    @Override
    public void move() {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车行驶开始时间");
        //实现开车
        try {
            Thread.sleep(new Random().nextInt(1000));
            System.out.println("汽车行驶中");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("汽车结束行驶。。。 汽车行驶时间：" + (endTime - startTime) + "毫秒");
    }
}
