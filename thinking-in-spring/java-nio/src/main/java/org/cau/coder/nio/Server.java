package org.cau.coder.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server {
    public static final int EOF = -1;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //  绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //  将接受的文件进行存储
        FileChannel fileChannel = FileChannel.open(Paths.get("server-test.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("服务端启动");
        SocketChannel socketChannel;

        while(true){
            socketChannel = serverSocketChannel.accept();
            System.out.println("接受到客户端传输数据");
            int len;
            while(EOF != (len = socketChannel.read(buffer))){
                buffer.flip();
                fileChannel.write(buffer);
                buffer.clear();
                System.out.println(len);
            }

            System.out.println("接收完成，通知客户端");
            byte[] feedBack = "服务端接收数据成功".getBytes();
            ByteBuffer tmpBuffer = ByteBuffer.allocate(feedBack.length);
            tmpBuffer.put(feedBack);
            //  ByteBuffer要传输，需要进行flip
            tmpBuffer.flip();
            socketChannel.write(tmpBuffer);
            //  发送完成，shutdown 通知客户端数据已经发送完成
            socketChannel.shutdownOutput();
        }
    }
}
/**
 服务端启动
 接受到客户端传输数据
 1024
 1024
 45
 接收完成，通知客户端
 */