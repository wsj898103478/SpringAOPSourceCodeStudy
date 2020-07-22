package com.wsjkk.impl;
/**
 * 策略设计模式
 * Applicator的apply()方法可以接受任何类型的Processor，并将其应用到一个Object对象上输出结果。
 * 像本例中这样，创建一个能根据传入的参数类型从而具备不同行为的方法称为策略设计模式。
 * 方法包含算法中不变的部分，策略包含变化的部分。策略就是传入的对象，它包含要执行的代码。在这里，Processor对象是策略，main()方法展示了三种
 * 不同的应用于String s上的策略。
 */

import java.util.Arrays;

class Processor{
    public String name(){
        return getClass().getSimpleName();
    }

    public Object process(Object input){
        return input;
    }
}

class Upcase extends Processor{
    //返回协变类型

    @Override
    public String process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class Downcase extends Processor{
    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter extends Processor {
    @Override
    public String process(Object input) {
        // split() divides a String into pieces:
        return Arrays.toString(((String) input).split(" "));
    }
}

public class Applicator {
    public static void apply(Processor p,Object s){
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }


    public static void main(String[] args) {
        String s = "We are such stuff as dreams are made on";
        apply(new Upcase(),s);
        apply(new Downcase(),s);
        apply(new Splitter(),s);
    }
}
