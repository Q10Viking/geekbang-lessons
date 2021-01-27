package org.geekbang.thinking.in.spring.dependency.exception;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class NoSucnBeanDefinitionExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();

        applicationContext.getBean(User.class);

        applicationContext.close();
    }
}
/**
 Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException:
 */