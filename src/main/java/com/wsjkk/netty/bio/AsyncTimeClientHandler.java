package com.wsjkk.netty.bio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeClientHandler implements CompletionHandler<Void,AsyncTimeClientHandler>,Runnable {

    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;

    public AsyncTimeClientHandler(String host,int port){
        this.host = host;
        this.port = port;
        try{
            //创建一个新的AsynchronousSocketChannel对象。
            client = AsynchronousSocketChannel.open();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        //创建CountDownLatch进行等待，防止异步操作没有执行完成线程就退出。
        latch = new CountDownLatch(1);
        //通过connect方法发起异步操作，他有两个参数
        //A attachment :AsynchronousSocketChannel的附件，用于回调通知时作为入参被传递，调用者可以自定义
        //CompletionHandler<Void,? super A> handler:异步操作回调通知接口，由调用者实现
        client.connect(new InetSocketAddress(host,port),this,this);
        try{
            latch.await();
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }
        try{
            client.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        //我们创建请求消息体，对其进行编码
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        //复制到发送缓冲区
        writeBuffer.put(req);
        writeBuffer.flip();
        //调用AsynchronousSocketChannel的write方法进行异步写。
        //实现CompletionHandler<Integer, ByteBuffer>接口用于写操作完成后的回调
        client.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if(buffer.hasRemaining()){
                    client.write(buffer,buffer,this);
                }else{
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body;
                            try{
                                body = new String(bytes,"UTF-8");
                                System.out.println("Now is :"+body);
                                latch.countDown();
                            }catch (UnsupportedEncodingException e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    client.close();
                                }catch (IOException e){
                                    //ignore on close
                                }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    client.close();
                    latch.countDown();
                }catch (IOException e){
                    //ignore on close
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        exc.printStackTrace();
        try{
            client.close();
            latch.countDown();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
