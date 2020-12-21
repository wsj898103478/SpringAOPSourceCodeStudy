package com.wsjkk.dutychain;

/**
 * 总经理
 */
public class BigManager implements IHandler{
    @Override
    public void handlerLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("总经理审批：同意。");
    }
}
