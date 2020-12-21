package com.wsjkk.dutychain;

/**
 * 部门经理
 */
public class Manager implements IHandler{
    @Override
    public void handlerLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("部门经理审批：同意。");
    }
}
