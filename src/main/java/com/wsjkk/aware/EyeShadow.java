package com.wsjkk.aware;

public class EyeShadow extends Decorator{

    public EyeShadow(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("眼影（");
        showable.show();
        System.out.print("）");
    }
}
