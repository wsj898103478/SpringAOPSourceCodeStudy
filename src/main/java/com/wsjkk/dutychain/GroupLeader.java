package com.wsjkk.dutychain;

/**
 * 小组长
 */
public class GroupLeader implements IHandler{
    @Override
    public void handlerLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("小组长审批：同意。");
    }
}
