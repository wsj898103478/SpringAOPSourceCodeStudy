package com.wsjkk.concurrent.singleton;

/**
 * 线程安全，性能又高的，写法最常见
 */
public class SafeAndGoogPerformSingleton {
    private static SafeAndGoogPerformSingleton instance;
    private static byte[] lock = new byte[0];
    private SafeAndGoogPerformSingleton(){

    }

    public static SafeAndGoogPerformSingleton getInstance(){
        if(instance == null){
            synchronized (lock){
                if(instance == null){
                    instance = new SafeAndGoogPerformSingleton();
                }
            }
        }
        return instance;
    }
}
