package com.wsjkk.proxy.jdkproxy;

import com.wsjkk.proxy.staticproxy.Car2;
import com.wsjkk.proxy.staticproxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 弊端：
 * 1.只能代理实现了接口的类
 * 2.没有实现接口的类不能实现JDK的动态代理
 */
public class Client {
    public static void main(String[] args) {
        Car2 car2 = new Car2();
        InvocationHandler timeHandler = new TimeHandler(car2);

        /**
         * newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
         * ClassLoader 类加载器
         * interfaces 接口
         * h 引用
         */
        Moveable m = (Moveable) Proxy.newProxyInstance(car2.getClass().getClassLoader(),car2.getClass().getInterfaces(),timeHandler);
        InvocationHandler logHandler = new LogHandler(m);
        Moveable m2 = (Moveable) Proxy.newProxyInstance(car2.getClass().getClassLoader(),car2.getClass().getInterfaces(),logHandler);
        m2.move();
    }
}
