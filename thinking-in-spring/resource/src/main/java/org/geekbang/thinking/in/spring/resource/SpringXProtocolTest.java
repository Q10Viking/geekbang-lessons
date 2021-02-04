package org.geekbang.thinking.in.spring.resource;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class SpringXProtocolTest {

    // 启动时指定虚拟机参数 -Djava.protocol.handler.pkgs=org.geekbang.thinking.in.spring.resource
    public static void main(String[] args) throws IOException {
        URL url = new URL("springx:///META-INF/springx.properties");
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();
        String content = StreamUtils.copyToString(is, Charset.forName("utf-8"));
        System.out.println(content);
    }
}
/**
 springxurl = 测试springx协议
 */