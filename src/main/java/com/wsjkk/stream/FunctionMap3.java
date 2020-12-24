package com.wsjkk.stream;

import java.util.stream.Stream;

public class FunctionMap3 {
    public static void main(String[] args) {
        Stream.of("5","7","9")
                .mapToInt(Integer::parseInt)
                .forEach(n -> System.out.format("%d ",n));
        System.out.println();
    }
}
