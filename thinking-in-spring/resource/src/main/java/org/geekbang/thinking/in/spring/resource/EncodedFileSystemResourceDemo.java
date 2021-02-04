package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;

import java.io.*;
import java.nio.file.FileSystem;

//  读取当前类文件
public class EncodedFileSystemResourceDemo {
    public static void main(String[] args) throws IOException {
        //  D:\Code\Java\Spring\geekbang-lessons\thinking-in-spring\resource\src\main\java\org\geekbang\thinking\in\spring\resource\EncodedFileSystemResourceDemo.java
        //  反斜杠正确的形式
        String path = System.getProperty("user.dir")+"/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";
        FileSystemResource fileSystemResource = new FileSystemResource(path);
        EncodedResource encodedResource = new EncodedResource(fileSystemResource, "utf-8");

        //  字符流
        try(Reader reader = encodedResource.getReader()){
            //  使用apache commons-io工具
            String s = IOUtils.toString(reader);
            System.out.println(s);
        }
    }
}
