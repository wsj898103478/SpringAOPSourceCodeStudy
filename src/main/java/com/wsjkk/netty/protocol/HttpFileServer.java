package com.wsjkk.netty.protocol;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/main/java/com/wsjkk/netty/";

    public void run(final int port,final String url){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder",new HttpRequestEncoder());
                            ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
                        }
                    });
            ChannelFuture future = bootstrap.bind("192.168.3.220",port).sync();
            System.out.println("HTTP文件目录服务器启动，网址是：" + "http://192.168.3.220:" + port + url);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8090;
        String url = DEFAULT_URL;
        new HttpFileServer().run(port,url);
    }
}
