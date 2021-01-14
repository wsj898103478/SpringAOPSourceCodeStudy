package com.wsjkk.netty.bio;

public class NIOTimeServer {
    public static void main(String[] args) {
        int port = 8080;
        try{
            port = Integer.valueOf(port);
        }catch (Exception e){
            e.printStackTrace();
        }
        MutiplexerTimeServer mutiplexerTimeServer = new MutiplexerTimeServer(port);
        new Thread(mutiplexerTimeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
