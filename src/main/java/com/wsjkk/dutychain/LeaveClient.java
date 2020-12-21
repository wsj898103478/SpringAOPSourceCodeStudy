package com.wsjkk.dutychain;

/**
 * 这个程序写出来是有问题的。
 *
 * 　　如果老板还要审批该怎么办？在外面继续加个if判断？一个优秀的程序应该具有高内聚低耦合的特性，这个程序审批规则写在外面是不合理的。
 *
 * 　　正确的逻辑应该是什么样的呢？ 应该是小花递了请假条之后，就只需要等结果即可，而不是找了这个审批，回来后又跑去找那个审批。
 */
public class LeaveClient {
    public static void main(String[] args) {
        //请假条来一张
        ILeave leave = new Leave("小花",5,"身体不适");
        //小组长第一个审批
        IHandler groupLeader = new GroupLeader();
        groupLeader.handlerLeave(leave);

        if(leave.getNum() >= 3){
            IHandler manager = new Manager();
            manager.handlerLeave(leave);
        }
        if(leave.getNum() >= 7){
            IHandler bigManager = new BigManager();
            bigManager.handlerLeave(leave);
        }
    }
}
