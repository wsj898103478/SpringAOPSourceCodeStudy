package com.wsjkk.facade;

class Chef {
    public void cook() {
        System.out.println("cook!");
    }
}

class Waiter {

    public void order() {
        System.out.println("order");
    }

    public void serve() {
        System.out.println("serve");
    }
}

class Cleaner {
    public void clean() {
        System.out.println("clean");
    }

    public void wash() {
        System.out.println("wash");
    }
}

public class Facade {
    private VegVendor vv;
    private Chef chef;
    private Waiter waiter;
    private Cleaner cleaner;

    public Facade() {
        this.vv = new VegVendor();
        //开门前就找菜贩子准备好蔬菜
        vv.sell();
        //当然还得雇佣好各类饭店服务人员
        this.chef = new Chef();
        this.waiter = new Waiter();
        this.cleaner = new Cleaner();
    }

    public void provideService() {
        //接待，入座，点菜
        waiter.order();
        //找厨师做饭
        chef.cook();
        //上菜
        waiter.serve();
        //收拾桌子，洗碗，以及其他工序……
        cleaner.clean();
        cleaner.wash();
    }
}
