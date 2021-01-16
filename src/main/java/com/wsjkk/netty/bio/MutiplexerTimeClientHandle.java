package com.wsjkk.netty.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MutiplexerTimeClientHandle implements Runnable{
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    public MutiplexerTimeClientHandle(String host,int port){
        this.host = host;
        this.port = port;
        try{
            //初始化NIO的多路复用器
            selector = Selector.open();
            //初始化SocketChannel对象
            socketChannel = SocketChannel.open();
            //设置为异步非阻塞模式
            socketChannel.configureBlocking(false);
            //TODO
            //设置SocketChannel的TCP参数，例如接收和发送的TCP缓冲区大小。
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public void run() {
        try{
            //用于发送连接请求，作为示例，连接是
            doConnect();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop){
            try{
                //轮询多路复用器Selector
                selector.select(1000);
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                SelectionKey key = null;
                while (iterator.hasNext()){
                    key = iterator.next();
                    iterator.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key!=null){
                            key.channel().close();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.exit(1);
            }
        }
        if(selector!=null){
            try {
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        //首先对SocketChannel的connect()操作进行判断.
        boolean connect = socketChannel.connect(new InetSocketAddress(host, port));
        //如果连接成功
        if(connect){
            //将SocketChannel注册到多路复用器Selector上，注册SelectorKey.OP_READ
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWrite(socketChannel);
        //没有直接连接成功，说明服务端没有返回TCP握手应答消息，但这不代表连接失败
        }else {
            //需要将SocketChannel注册到多路复用器Selector上，注册SelectionKey.OP_CONNECT
            //当服务端返回TCP syn-ack消息后，Selector就能够轮询到这个SocketChannel处于连接就绪状态
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        //构造请求消息体
        byte[] req = "QUERY TIME ORDER".getBytes();
        //对其编码，
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        //写入到发送缓冲区中
        writeBuffer.put(req);
        writeBuffer.flip();
        //调用SocketChannel的write方法进行发送
        socketChannel.write(writeBuffer);
        //TODO
        //由于发送时异步的，所以会存在“半包写”问题
        //通过hasRemaining()方法对发送结果进行判断，如果缓冲区中的消息全部发送完成，打印"Send order 2 server succeed."
        if(!writeBuffer.hasRemaining()){
            System.out.println("Send order 2 server succeed.");
        }
    }

    private void handleInput(SelectionKey key) throws IOException{
        //先对SelectionKey进行判断，看它处于什么状态？
        if(key.isValid()){
            SocketChannel sc = (SocketChannel) key.channel();
            //如果是处于连接状态，说明服务端已经返回ACK应答消息。
            if(key.isConnectable()){
                //我们需要对连接结果进行判断，调用SocketChannel的finishConnect()方法
                //如果返回为true，说明客户端连接成功
                if(sc.finishConnect()){
                    //将SocketChannel注册到多路复用器上，注册SelectionKey.OP_READ操作位
                    //监听网络读操作，然后发送请求消息给服务端
                    sc.register(selector,SelectionKey.OP_READ);
                    doWrite(sc);
                //如果返回值为false或者直接抛出IOException，说明连接失败。
                }else {
                    System.exit(1);  //连接失败，进程退出
                }
                //我们继续分析客户端是如何杜宇时间服务器应答消息的，
                //如果客户端接收到了服务端的应答消息，则SocketChannel是可读的
                if(key.isReadable()){
                    //由于无法事先判断应答码流的大小，我们就预分配1MB的接收缓冲区用于读取应答消息
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    //调用SocketChannel的read()方法进行异步读取操作，由于是异步操作，所以必须对读取的结果进行判断
                    int readBytes = sc.read(readBuffer);
                    if(readBytes > 0){
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        String body = new String(bytes,"UTF-8");
                        System.out.println("Now is : " + body);
                        this.stop = true;
                    }else if(readBytes < 0){
                        //对端链路关闭
                        key.cancel();
                        sc.close();
                    }else{
                        ; //读到0字节，忽略
                    }
                }
            }
        }

    }


}
