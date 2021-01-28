package org.cau.hzz.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionMethodDemo {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3);

        List<String> res = new ArrayList<>(3);
        Consumer<Integer> doubleVal = i -> res.add(String.valueOf(i*2));
        nums.forEach(doubleVal);

        System.out.println(res);    // [2, 4, 6]
    }
}
