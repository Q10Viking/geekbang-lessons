package org.cau.coder;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

public class EncodeDemo {
    public static void main(String[] args) throws CharacterCodingException {
        Charset utf8 = Charset.forName("utf-8");
        Charset gbk = Charset.forName("gbk");
        //  编码器
        CharsetEncoder utf8Encoder = utf8.newEncoder();
        CharsetEncoder gbkEncoder = gbk.newEncoder();

        CharBuffer buffer = CharBuffer.allocate(4);
        buffer.put("中国");
        //  编码：字符串->字节数组
        buffer.flip();
        ByteBuffer utf8Buffer = utf8Encoder.encode(buffer);
        System.out.println(Arrays.toString(utf8Buffer.array()));

        buffer.rewind();
        ByteBuffer gbkBuffer = gbkEncoder.encode(buffer);
        System.out.println(Arrays.toString(gbkBuffer.array()));


    }
}
/**
 [-28, -72, -83, -27, -101, -67]
 [-42, -48, -71, -6]
 */