package com.wsjkk.principle.dip;

/**
 * 当Tom还想学习AI人工智能的课程，这个时候，业务扩展，我们的代码要从
 * 底层到高层（调用层）一次修改代码
 *
 * 切记：以抽象为基准比以细节为基准搭建起来的架构要问稳定得多，
 * 因此在拿到需求之后，要面向接口编程，先顶层再细节来设计代码结构。
 */
public class Tom {
//    public void studyJavaCourse(){
//        System.out.println("Tom在学习Java的课程");
//    }
//
//    public void studyPythonCourse(){
//        System.out.println("Tom在学习Python的课程");
//    }

    private ICourse course;

    public Tom(ICourse course) {
        this.course = course;
    }
    public void study(){
        course.study();
    }
}
