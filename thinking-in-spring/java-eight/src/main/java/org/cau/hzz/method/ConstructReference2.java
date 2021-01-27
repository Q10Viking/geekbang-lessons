package org.cau.hzz.method;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConstructReference2 {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("橘右京","马可波罗","公孙离");

        fakePerson(names,Person::new)
                .forEach(System.out::println);
    }

    private static List<Person> fakePerson(List<String> names, Function<String,Person> func){
        Supplier<List<Person>> list =  ArrayList::new;
        List<Person> people = list.get();

        for(String name: names){
            people.add(func.apply(name));
        }

        return people;
    }
}
/**
 Person{name='橘右京'}
 Person{name='马可波罗'}
 Person{name='公孙离'}
 */