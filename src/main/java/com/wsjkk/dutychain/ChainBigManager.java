package com.wsjkk.dutychain;

/**
 * 总经理处理
 */
public class ChainBigManager extends Handler{
    public ChainBigManager(){
        super(Handler.NUM_SEVEN);
    }

    @Override
    protected void handleLeave(ILeave leave) {
        System.out.println(leave.getName() + "请假" + leave.getNum() + "天," + leave.getContent() + "。");
        System.out.println("总经理审批：同意。");
    }
}
