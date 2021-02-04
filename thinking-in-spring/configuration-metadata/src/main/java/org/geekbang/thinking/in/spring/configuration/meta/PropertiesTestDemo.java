package org.geekbang.thinking.in.spring.configuration.meta;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PropertiesTestDemo {

    public static void main(String[] args) throws IOException {
        String resourceName = "META-INF/spring.handlers";

        ClassPathResource resource = new ClassPathResource(resourceName);

        InputStream is = resource.getInputStream();
        Properties properties = new Properties();
        try{
            properties.load(is);
        }finally {
            is.close();
        }
        //  {http://time.geekbang.org/schema/users=org.geekbang.thinking.in.spring.configuration.meta.UsersNamespaceHandler}
        System.out.println(properties);
    }
}

