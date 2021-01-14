package com.wsjkk.facade;

class VegVendor{
    public void sell(){
        System.out.println("买菜!");
    }
}

class GirlFriend{
    public void cook(){
        System.out.println("厨师烧饭!");
    }
}


public class HaveLunch {
    public void eat(){
        System.out.println("吃饭!");
    }

    public static void main(String[] args) {
        //买菜
        VegVendor vegVendor = new VegVendor();
        vegVendor.sell();
        //做饭
        GirlFriend girlFriend = new GirlFriend();
        girlFriend.cook();
        //吃饭
        HaveLunch haveLunch = new HaveLunch();
        haveLunch.eat();
    }
}
