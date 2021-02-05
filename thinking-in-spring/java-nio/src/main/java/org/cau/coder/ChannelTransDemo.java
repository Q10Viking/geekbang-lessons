package org.cau.coder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelTransDemo {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\11930\\Videos\\李志 2014_15 i_O 跨年音樂會.mp4";
        FileChannel inputChannel = new FileInputStream(path).getChannel();
        FileChannel outputChannel1 = new FileOutputStream("C:\\Users\\11930\\Videos\\video1.mp4").getChannel();

        inputChannel.transferTo(0,inputChannel.size(),outputChannel1);
    }
}