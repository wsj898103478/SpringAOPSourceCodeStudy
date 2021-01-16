package com.wsjkk.netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class NettyTimeServerHandler extends ChannelInboundHandlerAdapter {
    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将msg转换成Netty的ByteBuf对象。
        //ByteBuf类似与JDK中的java.nio.ByteBuffer对象，不过它提供了更加强大和灵活的功能。
        ByteBuf buf = (ByteBuf)msg;
        //readableBytes()方法可以获取缓冲区可读的字节数
        byte[] req = new byte[buf.readableBytes()];
        //通过ByteBuf的readBytes方法将缓冲区中的字节数组复制到新建的byte数组中
        buf.readBytes(req);
        //通过new String构造函数获取请求消息
        String body = new String(req,"UTF-8").substring(0,req.length - System.getProperty("line.separator").length());
        System.out.println("The time server receive order : " + body + " ;the counter is : " + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        //通过ChannelHandlerContext的write方法异步发送应答消息到客户端
        ctx.write(resp);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将消息发送队列中的消息写入到SocketChannel中发送给对方
        //从性能角度考虑，为了频繁地唤醒Selector进行消息发送，Netty的write方法并不直接将消息写入SocketChannel中，
        //调用write方法只是把待发送的消息放到发送缓冲数组中，再通过调用flush方法，将发送缓冲区
        //中的消息全部写到SocketChannel中
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //当发生异常时，关闭ChannelHandlerContext，释放和ChannelHandlerContext相关的句柄等资源
        ctx.close();
    }
}
