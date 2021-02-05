package org.geekbang.thinking.in.spring.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class TravelProperties {
    public static void main(String[] args) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/default.properties");

        Properties properties = new Properties();
        properties.load(is);

       Map<String,String> map = new HashMap<>();
        for(Map.Entry<Object, Object> entry: properties.entrySet()){
            map.put((String)entry.getKey(),(String)entry.getValue());
        }

        System.out.println(map);
    }
}
/**
 {school=CAU, name=Q10Viking}
 */