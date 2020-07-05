package com.wsjkk.proxy.jdkproxy;

import com.wsjkk.proxy.staticproxy.Car2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandler implements InvocationHandler {
    private Object target;

    public TimeHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 代理参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("汽车行驶开始时间");
        method.invoke(target);
        Long endTime = System.currentTimeMillis();
        System.out.println("汽车结束行驶。。。 汽车行驶时间：" + (endTime - startTime) + "毫秒");
        return null;
    }
}
