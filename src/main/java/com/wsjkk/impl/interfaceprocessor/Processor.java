package com.wsjkk.impl.interfaceprocessor;

public interface Processor {
    default String name(){
        return getClass().getSimpleName();
    }

    Object process(Object input);

}
