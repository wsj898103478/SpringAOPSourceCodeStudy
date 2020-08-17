package com.wsjkk.concurrent.singleton;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全，性能又高的，这种写法也最为常见
 */
public class SafeAndGoodPerformSingleton2 {
    private static SafeAndGoodPerformSingleton2 instance;

    private static ReentrantLock lock = new ReentrantLock();

    private SafeAndGoodPerformSingleton2(){

    }
    public static SafeAndGoodPerformSingleton2 getInstance(){
        if(instance == null){
            lock.lock();
            if(instance == null){
                instance = new SafeAndGoodPerformSingleton2();
            }
            lock.unlock();
        }
        return instance;
    }
}
