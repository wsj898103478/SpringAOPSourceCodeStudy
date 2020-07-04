package com.wsjkk.aware;

/**
 * 化妆品类改成抽象类，重写show()方法，
 * 这里留给子类具体的某个化妆品去做装饰
 *
 */
public abstract class Decorator implements Showable{

    Showable showable;

    public Decorator(Showable showable) {
        this.showable = showable;
    }

    @Override
    public void show() {
//        System.out.print("粉饰（");
        showable.show();  //直接调用不做任何的修饰
//        System.out.print(")");
    }
}
