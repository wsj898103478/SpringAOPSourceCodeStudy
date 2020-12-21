package com.wsjkk.dutychain;

public class Leave implements ILeave{
   private String name;

    private int num;

    private String content;

    public Leave(String name, int num, String content) {
        this.name = name;
        this.num = num;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
