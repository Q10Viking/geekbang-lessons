package org.cau.hzz.method;

import java.util.function.BiFunction;
import java.util.function.Function;

public class SpecialTypeReference {
    public static void main(String[] args) {
        String str = "HelloWorld";
        doOneArgument(str, x -> x.length());
        doOneArgument(str,String::length);

        doTwoArgument(str,"Hello",(x,y) -> x.startsWith(y));
        doTwoArgument(str, "Hello", String::startsWith);
    }

    private static <T,R> R doOneArgument(T src, Function<T,R> func){
        return func.apply(src);
    }

    private static <T,R> R doTwoArgument(T str1, T str2, BiFunction<T,T,R> func){
        return func.apply(str1,str2);
    }
}
