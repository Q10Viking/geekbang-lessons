package org.cau.coder;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileDemo {
    private static final int EOF = -1;
    public static void main(String[] args) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        try(
            FileChannel inputChannel = new FileInputStream("images/1.jpg").getChannel();
            FileChannel outputChannel = new FileOutputStream("images/1-copy.jpg").getChannel();
        ){
            while(EOF != inputChannel.read(buffer)){
                //  开启读模式
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }
            System.out.println("finished");
        }
    }
}
