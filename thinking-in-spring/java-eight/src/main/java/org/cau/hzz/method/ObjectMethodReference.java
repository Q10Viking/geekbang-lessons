package org.cau.hzz.method;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ObjectMethodReference {
    public static void main(String[] args) {
        List<Employ> employList = Arrays.asList(
                new Employ("橘右京", 38, BigDecimal.valueOf(3800)),
                new Employ("李白", 5, BigDecimal.valueOf(100)),
                new Employ("妲己", 25, BigDecimal.valueOf(2500)),
                new Employ("元歌", 99, BigDecimal.valueOf(9999))
        );
        ComparatorProvider provider = new ComparatorProvider();
        employList.sort(provider::compareBySalary);
        employList.forEach(System.out::println);
    }
}

class ComparatorProvider{
    public int compareByAge(Employ o1, Employ o2) {
        return o1.getAge().compareTo(o2.getAge());
    }

    public int compareByName(Employ o1, Employ o2) {
        return o1.getName().compareTo(o2.getName());
    }

    public int compareBySalary(Employ o1, Employ o2) {
        return o1.getAge().compareTo(o2.getAge());
    }
}
/**
 Employ{name='李白', age=5, salary=100}
 Employ{name='妲己', age=25, salary=2500}
 Employ{name='橘右京', age=38, salary=3800}
 Employ{name='元歌', age=99, salary=9999}
 */