package org.geekbang.thinking.in.spring.configuration.meta;

import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PropertiesTestDemo {

    public static void main(String[] args) throws IOException {
        String resourceName = "META-INF/spring.handlers";
        //  相比 new FileInputStream() 容易解决classpath路径问题
        InputStream is = PropertiesTestDemo.class.getClassLoader().getResourceAsStream(resourceName);

        Properties props = new Properties();
        try{
            props.load(is);
        }finally {
            is.close();
        }

        //  {http://time.geekbang.org/schema/users=org.geekbang.thinking.in.spring.configuration.meta.UsersNamespaceHandler}

        //  转化为Map
        Map<String,String> map = new HashMap(props);
        System.out.println(map);


        Properties newPro = new Properties();

        newPro.putAll(map);

        System.out.println(newPro);


    }
}

