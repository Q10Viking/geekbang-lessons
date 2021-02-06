package org.cau.coder;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ScatterReadDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("test.txt", "r");
        RandomAccessFile file2 = new RandomAccessFile("test2.txt", "rw");
        FileChannel channel1 = file.getChannel();
        FileChannel channel2 = file2.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        ByteBuffer buffer2 = ByteBuffer.allocate(20);

        ByteBuffer[] buffers = {buffer1,buffer2};

        //  分散读
        while(-1 != channel1.read(buffers)){
            //  ByteBuffer一定要开启读模式
            Arrays.stream(buffers).forEach(ByteBuffer::flip);
            //  分散写
            channel2.write(buffers);
            System.out.println("reading... ...");
            //  ByteBuffer 一定要清理
            Arrays.stream(buffers).forEach(ByteBuffer::clear);
        }
    }
}
