package com.wsjkk.dutychain;

/**
 * 部门经理处理
 */
public class ChainManager extends Handler{
    public ChainManager(){
        super(Handler.NUM_THREE,Handler.NUM_SEVEN);
    }
    @Override
    protected void handleLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("部门经理审批：同意。");
    }
}
