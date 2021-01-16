package com.wsjkk.netty.bio;

public class AsyncTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new AsyncTimeClientHandler("localhost",port),"AIO-AsyncTimeClientHandler-001").start();
    }
}
