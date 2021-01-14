package com.wsjkk.prototype;

/**
 * 代码第5行，初始化只接收x坐标，因为敌机一开始是从顶部出来所以纵坐标y必然是0。
 * 此类只提供getter而没有setter，也就是说只能在初始化时确定敌机的横坐标x，
 * 后续是不需要更改坐标了，只要连续调用第17行的fly方法即可让飞机跟雨点一样往下砸。
 */
public class EnemyPlan {
    private int x;//敌机横坐标
    private int y = 0; //敌机纵坐标

    public EnemyPlan(int x){ //构造器
        this.x = x;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void fly(){ //让敌机飞
        y++; //每调用一次，敌机飞行时纵坐标+1
    }
}
