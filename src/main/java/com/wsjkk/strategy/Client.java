package com.wsjkk.strategy;

public class Client {
    public static void main(String[] args) {
        MemberStrategy memberStrategy = new AdvancedMemberStrategy();
        Price price = new Price(memberStrategy);
        double quote = price.quote(300);
        System.out.println("图书的最终价格为：" + quote);
    }
}
