package com.wsjkk.springioc;

public class Terminal {
    private String name;
    private String word;

    public void myInit(){
        System.out.println("自定义初始化");
    }

    public void mydestroy(){
        System.out.println("自定义销毁");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
