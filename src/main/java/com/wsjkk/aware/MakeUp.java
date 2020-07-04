package com.wsjkk.aware;

/**
 * 定妆
 */
public class MakeUp extends Decorator{

    public MakeUp(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("定妆（");
        showable.show();
        System.out.print("）");
    }
}
