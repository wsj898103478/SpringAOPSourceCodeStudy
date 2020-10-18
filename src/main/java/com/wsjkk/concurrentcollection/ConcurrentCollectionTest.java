package com.wsjkk.concurrentcollection;

import org.junit.Test;

import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollectionTest {
    @Test
    public void test01(){
        Hashtable<String,Integer> numbers = new Hashtable<>();
        numbers.put("one",1);
        numbers.put("two",2);
        numbers.put("three",3);
    }

    @Test
    public void test02(){
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("one",1);
        map.put("two",2);

    }

    @Test
    public void test03(){
        StringBuilder stringBuilder = new StringBuilder();
        StringBuffer stringBuffer = new StringBuffer();
    }

}
