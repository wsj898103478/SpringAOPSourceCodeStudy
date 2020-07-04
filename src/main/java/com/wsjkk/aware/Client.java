package com.wsjkk.aware;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) throws FileNotFoundException {

        //用装饰器包裹女孩show出来
//        new Decorator(new Girl()).show();
        new MakeUp(new EyeShadow(new FoundationMakeup(new Girl()))).show();
        new BufferedReader(new InputStreamReader(new FileInputStream("")));
        //结果：粉饰（女孩的素颜）
    }

}
