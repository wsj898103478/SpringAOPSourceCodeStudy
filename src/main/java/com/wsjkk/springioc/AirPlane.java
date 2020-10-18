package com.wsjkk.springioc;

public class AirPlane {
    private String name;
    private String password;
    private String length;

    public AirPlane(){
        System.out.println("飞机创建");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
