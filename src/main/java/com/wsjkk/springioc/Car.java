package com.wsjkk.springioc;

public class Car {
    private String carName;
    private String carNumber;

    public Car() {
        System.out.println("车初始化完成");
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }
}
