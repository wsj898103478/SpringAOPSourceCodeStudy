package com.wsjkk.impl;

/**
 * 模板方法设计模式的一个版本
 */
public interface Operations {
    void execute();

    /**
     * runOps()是一个模板方法。runOps()使用可变参数列表
     * @param ops
     */
    static void runOps(Operations... ops){
        for (Operations op:ops) {
            op.execute();
        }
    }

    static void show(String msg){
        System.out.println(msg);
    }
}
