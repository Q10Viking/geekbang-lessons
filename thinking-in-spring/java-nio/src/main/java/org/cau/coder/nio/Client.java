package org.cau.coder.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    public static final int EOF = -1;
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //  读取本地文件传输到服务端
        FileChannel fileChannel = FileChannel.open(Paths.get("test.txt"), StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(EOF != fileChannel.read(buffer)){
            buffer.flip();
            //  传输到服务端
            socketChannel.write(buffer);
            buffer.clear();
        }

        //  shutdown 通知发送给服务器的数据已经完成，这样服务器不用一直在等待读
        //  Shutdown the connection for writing without closing the channel.
        socketChannel.shutdownOutput();
        System.out.println("文件发送成功,等待反馈... ...");


        //  接受服务器端的反馈
        buffer = ByteBuffer.allocate(1024);
        buffer.clear();
        while(EOF != socketChannel.read(buffer)){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,buffer.limit()));
            buffer.clear();
        }
        System.out.println("关闭");

        fileChannel.close();
        socketChannel.close();
    }
}
/**
 文件发送成功,等待反馈... ...
 服务端接收数据成功
 关闭
 */