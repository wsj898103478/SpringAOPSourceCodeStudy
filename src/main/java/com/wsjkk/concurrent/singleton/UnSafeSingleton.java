package com.wsjkk.concurrent.singleton;

/**
 * 线程不安全的，不正确的写法
 */
public class UnSafeSingleton {
    private static UnSafeSingleton unSafeSingleton;
    private UnSafeSingleton(){

    }
    public static UnSafeSingleton getInstance(){
        if(unSafeSingleton == null){
            unSafeSingleton = new UnSafeSingleton();
        }
        return unSafeSingleton;
    }
}
