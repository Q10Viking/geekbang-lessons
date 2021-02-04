package org.geekbang.thinking.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

//  读取当前类文件
public class EncodedFileSystemResourceLoaderDemo {
    public static void main(String[] args) throws IOException {
        //  D:\Code\Java\Spring\geekbang-lessons\thinking-in-spring\resource\src\main\java\org\geekbang\thinking\in\spring\resource\EncodedFileSystemResourceDemo.java
        //  反斜杠正确的形式
        String path = System.getProperty("user.dir")+"/thinking-in-spring/resource/src/main/java/org/geekbang/thinking/in/spring/resource/EncodedFileSystemResourceDemo.java";

        FileSystemResourceLoader loader = new FileSystemResourceLoader();
        Resource resource = loader.getResource(path);
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");

        //  关闭reader
        try(Reader reader = encodedResource.getReader()){
            //  使用apache commons-io工具
            String s = IOUtils.toString(reader);
            System.out.println(s);
        }
    }
}
