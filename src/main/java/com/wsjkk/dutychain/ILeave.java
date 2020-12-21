package com.wsjkk.dutychain;

public interface ILeave {
    /**
     * 请假人姓名
     * @return
     */
    String getName();

    /**
     * 请假天数
     * @return
     */
    int getNum();

    /**
     * 请假内容
     * @return
     */
    String getContent();

}
