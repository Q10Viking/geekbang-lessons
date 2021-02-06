package org.cau.coder;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class ChartSetDemo {
    public static void main(String[] args) {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        map.forEach((k,v) -> {
            System.out.printf("%s = %s\n",k,v);
        });

    }
}
/**
 GBK = GBK
 UTF-8 = UTF-8
 US-ASCII = US-ASCII
 ... ...
 */