package com.wsjkk.principle.dip;

import org.junit.Test;

public class Client {
    @Test
    public void test1(){
        Tom tom = new Tom(new JavaCourse());
        tom.study();
//        tom.studyJavaCourse();
//        tom.studyPythonCourse();
    }
}
