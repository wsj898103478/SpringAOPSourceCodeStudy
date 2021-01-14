package com.wsjkk.concurrent;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(1);


    UserThreadFactory(String whatFeatureOfGroup){
        namePrefix = "From UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }


    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + nextId.getAndIncrement();
        //stackSize:the desired stack size for the new thread, or zero to indicate that this parameter is to be ignored.
        Thread thread = new Thread(null,task,name,0);
        System.out.println(thread.getName());
        return thread;
    }
}
