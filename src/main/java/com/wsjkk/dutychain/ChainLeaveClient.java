package com.wsjkk.dutychain;

public class ChainLeaveClient {

    public static void main(String[] args) {
        //请假条
        ILeave leave = new Leave("小花",5,"身体不适");
        //
        Handler groupLeader = new ChainGroupLeader();
        Handler manager = new ChainManager();
        Handler bigManager = new ChainBigManager();

        groupLeader.setNextHandler(manager);
        manager.setNextHandler(bigManager);

        groupLeader.submit(leave);
    }
}
