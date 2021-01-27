package org.geekbang.thinking.in.spring.dependency.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanInstantionExpectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //  CharSequence 是一个接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);
        applicationContext.registerBeanDefinition("apple",beanDefinitionBuilder.getBeanDefinition());

        applicationContext.refresh();
        applicationContext.getBean("apple");
        applicationContext.close();
    }
}

/**
 Caused by: org.springframework.beans.BeanInstantiationException:
 */