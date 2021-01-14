package com.wsjkk.netty.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {
    private ExecutorService executor;

    public TimeServerHandlerExecutePool(int maxPoolSize,int queueSize){
        executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));

    }
    public void execute(Runnable task){
        executor.execute(task);
    }

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }
}
