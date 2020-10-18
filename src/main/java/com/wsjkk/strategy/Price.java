package com.wsjkk.strategy;

public class Price {
    /**
     *
     */
    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy){
        this.memberStrategy = memberStrategy;
    }

    /**
     * 计算图书的价格
     * @param booksPrice 图书的原价
     * @return  计算出打折后的价格
     */
    public double quote(double booksPrice){
        return this.memberStrategy.calcPrice(booksPrice);
    }
}
