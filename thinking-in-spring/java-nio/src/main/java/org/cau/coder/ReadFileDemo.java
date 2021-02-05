package org.cau.coder;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileDemo {
    private static final int EOF = -1;
    public static void main(String[] args) throws IOException {

        //  channel
        RandomAccessFile file = new RandomAccessFile("test.txt", "r");
        FileChannel channel = file.getChannel();
        //  buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        while(EOF != channel.read(byteBuffer)){
            byteBuffer.flip();
            for (int i=0; i<byteBuffer.limit();i++){
                byte b = byteBuffer.get(i);
                System.out.print((char)b);
            }
        }
    }
}
/**
 hello world nio
 */