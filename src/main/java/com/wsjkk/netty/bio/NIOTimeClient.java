package com.wsjkk.netty.bio;

public class NIOTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new MutiplexerTimeClientHandle("localhost",port),"TimeClient-001").start();
    }
}
