package org.cau.hzz.method;

import java.util.Arrays;
import java.util.List;

public class ObjectStaticMethodReference {
    public static void main(String[] args) {
        List<String> games = Arrays.asList("狼人杀", "王者荣耀", "穿越火线");
        games.forEach(SimplePrinter::print);

    }
}

class SimplePrinter{
    public static void print(String str){
        System.out.println(str);
    }
}
