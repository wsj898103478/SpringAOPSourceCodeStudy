package com.wsjkk.strategy;

public class PrimaryMemberStrategy implements MemberStrategy{


    @Override
    public double calcPrice(double booksPrice) {
        return booksPrice;
    }
}
