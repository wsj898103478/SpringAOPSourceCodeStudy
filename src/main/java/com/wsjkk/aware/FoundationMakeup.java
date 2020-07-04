package com.wsjkk.aware;

/**
 * 粉底类
 * 把化妆品类改成了抽象类，重写show方法，但不做任何粉饰
 * 留给子类具体·的某个化妆品去做装饰。
 *
 */
public class FoundationMakeup extends Decorator{

    public FoundationMakeup(Showable showable) {
        super(showable); //调用化妆品父类注入
    }

    @Override
    public void show() {
        System.out.print("打粉底（");
        showable.show();
        System.out.print("）");
    }
}
