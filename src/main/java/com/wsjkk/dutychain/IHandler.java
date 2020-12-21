package com.wsjkk.dutychain;

public interface IHandler {
    /**
     * 处理请假条
     * @param leave
     */
    void handlerLeave(ILeave leave);
}
