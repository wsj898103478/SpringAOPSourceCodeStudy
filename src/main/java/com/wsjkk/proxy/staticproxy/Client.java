package com.wsjkk.proxy.staticproxy;

public class Client {
    public static void main(String[] args) {
//        new Car().move();
//        new Car3().move();
//        new Car4(new Car2()).move();
        new CarLogProxy(new CarTimeProxy(new Car2())).move();
    }
}
