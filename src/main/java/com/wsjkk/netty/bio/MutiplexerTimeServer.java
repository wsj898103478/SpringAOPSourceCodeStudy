package com.wsjkk.netty.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MutiplexerTimeServer implements Runnable{
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port
     */
    public MutiplexerTimeServer(int port){
        try{
            //打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
            serverSocketChannel = ServerSocketChannel.open();
            //绑定监听端口，
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            //设置连接为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //创建Reactor线程
            selector = Selector.open();
            //将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听ACCEPT事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    /**
     * 初始化多路复用器，绑定监听端口
     */
    @Override
    public void run() {

        while (!stop){
            try{
                //循环遍历selector，他的休眠时间为1s。无论是否有读写等事件发生，selector每隔1s都被唤醒一次。
                //selector也提供了一个无参的select方法：
                selector.select(1000);
                //当有处于就绪状态的Channel时，selector将返回该Channel的SelectionKey集合
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                //通过对就绪状态的Channel集合进行迭代，可以进行网络的异步读写操作
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            }catch (Throwable t){
                t.printStackTrace();
            }
        }
        if(selector != null){
            try{
                selector.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            //处理新接入的请求信息
            if(key.isAcceptable()){
                //Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                //上述操作，相当于完成了TCP的三次握手,TCP链路正式建立
                SocketChannel sc = ssc.accept();
                //将新创建的SocketChannel设置为异步非阻塞
                sc.configureBlocking(false);
                //添加新连接 add the new connection to the selector
                sc.register(selector,SelectionKey.OP_READ);
                //TODO
                //同时对其TCP参数进行设置，例如TCP接收和发送缓冲区的大小等
            }
            if(key.isReadable()){
                //读取数据
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    //将缓冲区当前的limit设置为position，position设置为0，用于后续对缓冲区的读取操作
                    readBuffer.flip();
                    //根据缓冲区可读的字节个数创建字节数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //调用ByteBuffer的get操作将缓冲区可读的字节数组复制到新创建的字节数组中
                    readBuffer.get(bytes);
                    //调用字符串的构造函数创建请求消息体
                    String body = new String(bytes,"UTF-8");
                    //打印
                    System.out.println("The time server receive order:" + body);
                    //如果请求指令是"QUERY TIME ORDER",则把服务器的当前时间编码后返回给客户端
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)? new Date(System.currentTimeMillis()).toString():"BAD ORDER";
                    //异步发送应答消息给客户端
                    doWrite(sc,currentTime);
                }else if(readBytes < 0){
                    key.cancel();
                    sc.close();
                }else{
                    ;//读到0字节，忽略
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if(response != null && response.trim().length()>0){
            //将字符串编码成字节数组
            byte[] bytes = response.getBytes();
            //根据字节数组的容量创建ByteBuffer
            ByteBuffer writerBuffer = ByteBuffer.allocate(bytes.length);
            //调用ByteBuffer的put操作将字节数组复制到缓冲区中
            writerBuffer.put(bytes);
            //对缓冲区进行flip操作
            writerBuffer.flip();
            //最后调用SocketChannel的write方法将缓冲区中的字节数组发送出去
            //需要指出的是，由于SocketChannel是异步非阻塞的，
            //并不保证一次能把需要发送的字节数组发送完，此时会出现"写半包"问题。
            //我们需要注册写操作，不断轮询Selector将没有发送完的ByteBuffer发送完毕
            //然后可以通过ByteBuffer的hasRemain()方法判断消息是否发送完成。
            channel.write(writerBuffer);
         }
    }
}
