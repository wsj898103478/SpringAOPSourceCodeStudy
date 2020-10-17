package com.wsjkk.principle.dip;

public class PythonCourse implements ICourse{
    @Override
    public void study() {
        System.out.println("Tom在学习Python课程");
    }
}
