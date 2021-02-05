package org.cau.coder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.function.BiConsumer;

public class CopyVideoDemo2 {
    private static final int EOF = -1;
    private static final int DEFAULT_SIZE = 1024;
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\11930\\Videos\\李志 2014_15 i_O 跨年音樂會.mp4";
        FileChannel inputChannel = new FileInputStream(path).getChannel();
        FileChannel outputChannel1 = new FileOutputStream("C:\\Users\\11930\\Videos\\video1.mp4").getChannel();
        FileChannel outputChannel2 = new FileOutputStream("C:\\Users\\11930\\Videos\\video2.mp4").getChannel();

        long size = inputChannel.size();
        copyFileUseDirectBuffer(inputChannel,outputChannel1);
        System.out.println("---------------------------------");
//        copyFileUseNotUseDirectBuffer(inputChannel,outputChannel2);

    }
    //  使用直接缓冲区进行操作
    public static void copyFileUseDirectBuffer(FileChannel in, FileChannel out){
        calculateTime("直接缓冲区",()->{
            ByteBuffer buffer = ByteBuffer.allocateDirect(DEFAULT_SIZE);
            copyFile(in,out,buffer);
        });
    }

    //  使用非直接缓冲区进行操作
    public static void copyFileUseNotUseDirectBuffer(FileChannel in, FileChannel out){
        calculateTime("非直接缓冲区",()->{
            ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_SIZE);
            copyFile(in,out,buffer);
        });
    }


    public static void calculateTime(String name, Runnable runnable){
        long start = System.currentTimeMillis();
        runnable.run();
        long during = System.currentTimeMillis() - start;
        System.out.printf("%s 运行 %d\n",name,during);
    }

    public static void copyFile(FileChannel in,FileChannel out,ByteBuffer buffer){
        try{
            while(EOF != in.read(buffer)){
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        }catch (IOException e){}
    }

}
/**
 直接缓冲区 运行 11511 10026 9672
 ---------------------------------
 非直接缓冲区 运行 20153 11584 12676
 */