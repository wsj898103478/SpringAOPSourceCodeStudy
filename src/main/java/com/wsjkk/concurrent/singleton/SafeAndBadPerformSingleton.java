package com.wsjkk.concurrent.singleton;

/**
 * 线程安全性，但是高并发性能不是很高的写法
 */
public class SafeAndBadPerformSingleton {
    private static SafeAndBadPerformSingleton instance;
    private SafeAndBadPerformSingleton(){

    }
    public static synchronized SafeAndBadPerformSingleton getInstance(){
        if(instance == null){
            instance = new SafeAndBadPerformSingleton();
        }
        return instance;
    }

}
