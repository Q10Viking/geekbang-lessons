package org.cau.coder.nio.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //  设置为非阻塞
        socketChannel.configureBlocking(false);

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            System.out.println("发送");
            socketChannel.write(getReadableBuffer(scanner.nextLine()));
        }
    }


    private static ByteBuffer getReadableBuffer(String data){
        StringBuilder builder = new StringBuilder();
        builder.append(LocalDateTime.now().toString())
                .append("\n")
                .append(data);

        byte[] dataBytes = builder.toString().getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(dataBytes.length);
        buffer.put(dataBytes);
        buffer.flip();
        return buffer;
    }
}
/**
 你好吗
 发送
 天道酬勤，事在人为，加油
 发送
 */