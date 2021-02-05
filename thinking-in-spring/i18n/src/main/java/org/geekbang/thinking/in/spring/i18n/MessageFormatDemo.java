package org.geekbang.thinking.in.spring.i18n;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class MessageFormatDemo {
    public static void main(String[] args) {
        int planet = 7;
        String event = "a disturbance in the Force";

        MessageFormat messageFormat = new MessageFormat("At {1,time,full} on {1,date,full}, there was {2} on planet {0,number,integer}.");
        String result1 = messageFormat.format(new Object[]{planet, new Date(), event});
        System.out.println(result1);


        //  重置模式
        messageFormat.applyPattern("本次语文考试 {1} {0}分 {2}");

        //  重置format
        messageFormat.setFormatByArgumentIndex(2,getChoiceFormat());
        Map<String, Double> students = getStudents();
        for (Map.Entry<String,Double> entry: students.entrySet()){
            Double grade = entry.getValue();
            String name = entry.getKey();
            String result = messageFormat.format(new Object[]{grade,name,grade});
            System.out.println(result);
        }
    }

    private static Map<String,Double> getStudents(){
        Map<String,Double> students = new LinkedHashMap<>();
        students.put("张山",37d);
        students.put("蔡文姬",68d);
        students.put("李四",76d);
        students.put("王五",87d);
        students.put("鲁班",99d);

        return students;
    }

    private static ChoiceFormat getChoiceFormat(){
        //  X matches j if and only if limit[j] ≤ X < limit[j+1]
        double[] limits = {0,60,70,80,90};;
        String[] gradesFormat = {"不及格","及格","中等","良好","优秀"};
        ChoiceFormat form = new ChoiceFormat(limits, gradesFormat);
        return form;
    }
}
/**
 At 上午10时15分53秒 GMT+08:00 on 2021年2月5日 星期五, there was a disturbance in the Force on planet 7.
 本次语文考试 张山 37分 不及格
 本次语文考试 蔡文姬 68分 及格
 本次语文考试 李四 76分 中等
 本次语文考试 王五 87分 良好
 本次语文考试 鲁班 99分 优秀
 */