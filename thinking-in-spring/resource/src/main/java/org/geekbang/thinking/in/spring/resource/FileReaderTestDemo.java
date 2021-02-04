package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;

import java.io.*;

//  传统形式
public class FileReaderTestDemo {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir")+"/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        //  读取一个文件
        File file = new File(path);
        //  转化为流
        InputStream inputStream = new FileInputStream(file);
        //  指定文件
        Reader reader =  new InputStreamReader(inputStream,"utf-8");

        //  转化为字符串
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024];

        final int EOF = -1;
        while(EOF != reader.read(buffer)){
            builder.append(buffer);
        }
        System.out.println(builder.toString());
    }
}
