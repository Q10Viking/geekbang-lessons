package org.geekbang.thinking.in.spring.i18n;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MessageFormatDemo2 {

    public static void main(String[] args) {
        //  X matches j if and only if limit[j] ≤ X < limit[j+1]
        double[] limits = {0,60,70,80,90};;
        String[] gradesFormat = {"不及格","及格","中等","良好","优秀"};
        ChoiceFormat form = new ChoiceFormat(limits, gradesFormat);

        Map<String,Double> students = new LinkedHashMap<>();
        students.put("张山",37d);
        students.put("蔡文姬",68d);
        students.put("李四",76d);
        students.put("王五",87d);
        students.put("鲁班",99d);


        for (Map.Entry<String,Double> entry: students.entrySet()){
            String format = form.format(entry.getValue());
            System.out.println(entry.getKey() + " " + format);
        }

    }
}
/**
 张山 不及格
 蔡文姬 及格
 李四 中等
 王五 良好
 鲁班 优秀
 */