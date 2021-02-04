package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class HandlerTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("x:/META-INF/default.properties");
        URLConnection urlConnection = url.openConnection();
        InputStream is = urlConnection.getInputStream();

//        InputStreamResource inputStreamResource = new InputStreamResource(is);
//        String content = ResourceUtils.getContent(inputStreamResource);
//        System.out.println(content);

        //  使用
        String content = StreamUtils.copyToString(is, Charset.forName("utf-8"));
        System.out.println(content);
    }
}
