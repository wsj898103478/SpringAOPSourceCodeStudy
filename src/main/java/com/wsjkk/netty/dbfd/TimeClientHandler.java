package com.wsjkk.netty.dbfd;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    private static final String ECHO_REQ = "hI,Lifeng.Weblcome to Netty.$_";
    public TimeClientHandler(){

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端跟服务端链路建立成功之后，循环发送100条消息，每发送一条就刷新一次，保证每条消息都会被写入Channel中。
        //按照我们的设计，服务端应该接收到100条查询时间指令的请求消息。
        for(int i = 0;i<10;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String)msg;
        System.out.println("Now is:"+body+";the counter is : "+ ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
