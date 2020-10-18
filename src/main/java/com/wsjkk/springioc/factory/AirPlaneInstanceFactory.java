package com.wsjkk.springioc.factory;

import com.wsjkk.springioc.AirPlane;

public class AirPlaneInstanceFactory {

    public AirPlane getAirPlane(String name){
        System.out.println("AirPlaneInstanceFactory 正在创建飞机");
        AirPlane airPlane = new AirPlane();
        airPlane.setLength("100");
        airPlane.setName(name);
        airPlane.setPassword("xxxxx");
        return airPlane;
    }

}
