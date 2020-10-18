package com.wsjkk.netty.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyTimeServer {
    public void bind(int port){
        /**
         * 创建两个NioEventLoopGroup实例
         * NioEventLoopeGroup是一个线程组，它包含一组线程组，它包含了一组NIO线程，专门用来网络事件的处理，实际上他们就是Reactor线程组
         * 创建两个的原因：一个用于服务端接受客户端的链接；另一个用于进行SocketChannel的网络读写
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            //创建ServerBootstrap对象，他是Netty用于启动NIO服务端的辅助启动类，目的是降低服务端的开发复杂度
            ServerBootstrap bootstrap = new ServerBootstrap();
            //将两个NIO线程组当作入参传递到ServerBootstrap中
            bootstrap.group(bossGroup,workerGroup)
                    //设置创建Channel为NioServerSocketChannel,它的功能对应于JDK NIO类库中的ServerSocketChannel类
                    .channel(NioServerSocketChannel.class)
                    //配置NioServerSocketChannel的TCP参数，将他的backlog设置为1024，
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //最后绑定I/O时间的处理类ChildChaanelHandler，作用类似于Reactor模式中的Handler类，主要用于处理网络I/O事件，例如记录日志、对消息进行编解码灯。
                    .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
