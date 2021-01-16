package com.wsjkk.netty.nio;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new NettyTimeServerHandler());
    }

    public static void main(String[] args) {
        int port = 8080;
        new NettyTimeServer().bind(port);
    }
}
