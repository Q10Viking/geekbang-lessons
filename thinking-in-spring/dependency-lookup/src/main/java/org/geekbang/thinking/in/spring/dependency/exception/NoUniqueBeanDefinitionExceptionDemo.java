package org.geekbang.thinking.in.spring.dependency.exception;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class NoUniqueBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();

        applicationContext.getBean(String .class);

        applicationContext.close();
    }

    @Bean
    public String bean1(){
        return "hello";
    }

    @Bean
    public String bean2(){
        return "world";
    }
}
/**
 Exception in thread "main" org.springframework.beans.factory.NoUniqueBeanDefinitionException:
 */