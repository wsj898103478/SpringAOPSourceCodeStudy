package com.wsjkk.springioc.factory;

import com.wsjkk.springioc.AirPlane;

public class AirPlaneStaticFactory {

    public static AirPlane getAirPlane(String name){
        System.out.println("调用AirPlaneStaticFactory创建飞机");
        AirPlane airPlane = new AirPlane();
        airPlane.setLength("100");
        airPlane.setName(name);
        airPlane.setPassword("xxxxx");
        return airPlane;
    }
}
