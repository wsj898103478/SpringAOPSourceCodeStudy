package com.wsjkk.principle.dip;

public class JavaCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("Tom在学习Java课程");
    }
}
