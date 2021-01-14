package com.wsjkk.prototype;

public class Bullet implements Cloneable{
    @Override
    protected Bullet clone() throws CloneNotSupportedException {
        return (Bullet)super.clone();
    }
}
