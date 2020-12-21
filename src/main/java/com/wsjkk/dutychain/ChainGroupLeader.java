package com.wsjkk.dutychain;

/**
 * 小组组长处理
 */
public class ChainGroupLeader extends Handler{
    public ChainGroupLeader(){
        super(Handler.NUM_ONE,Handler.NUM_THREE);
    }

    @Override
    protected void handleLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("小组长审批：同意。");
    }
}
