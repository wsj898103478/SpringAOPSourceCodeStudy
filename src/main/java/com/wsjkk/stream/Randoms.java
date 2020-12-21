package com.wsjkk.stream;

import org.junit.Test;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class Randoms {
    @Test
    public void test01(){
        new Random(47)
                .ints(5,20)
                .distinct()
                .limit(7)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void test02(){
        Random rand = new Random(47);
        SortedSet<Integer> rints = new TreeSet<>();
        while (rints.size() < 7){
            int r = rand.nextInt(20);
            if(r<5) continue;
            rints.add(r);
        }
        System.out.println(rints);
    }


}
