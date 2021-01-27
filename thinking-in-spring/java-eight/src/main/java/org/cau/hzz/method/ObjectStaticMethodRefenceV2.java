package org.cau.hzz.method;

import java.util.function.BiFunction;

public class ObjectStaticMethodRefenceV2 {
    public static void main(String[] args) {
        String r1 = playTwoArguments(1,2,(a,b)->IntegerUtils.join(a,b));
        String r2 = playTwoArguments(1,2,IntegerUtils::join);
        System.out.printf("r1 = %s \nr2 = %s \n",r1,r2);
    }

    private static <T> T playTwoArguments(Integer a, Integer b, BiFunction<Integer,Integer,T> func){
        return func.apply(a,b);
    }
}

abstract  class IntegerUtils{
    public static String join(Integer a,Integer b){
        return String.valueOf(a+b);
    }
}
