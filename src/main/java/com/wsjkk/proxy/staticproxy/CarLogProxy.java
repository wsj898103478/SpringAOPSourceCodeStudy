package com.wsjkk.proxy.staticproxy;

/**
 * 使用聚合的方式实现
 */
public class CarLogProxy implements Moveable{
    private Moveable moveable;

    public CarLogProxy(Moveable moveable) {
        this.moveable = moveable;
    }

    @Override
    public void move() {
        System.out.println("日志开始");
        moveable.move();
        System.out.println("日志结束");
    }
}
