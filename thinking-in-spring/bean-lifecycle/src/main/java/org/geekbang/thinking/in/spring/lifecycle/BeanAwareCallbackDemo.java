package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

public class BeanAwareCallbackDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  添加自定义的BeanPostProcessor
        beanFactory.addBeanPostProcessor(new BeanInstantiationLifeCycleDemo2.MyInstantiationAwareBeanPostProcessor());
        String[] xmlPath = {
                "META-INF/bean-constructor-dependency-inject.xml",
                "META-INF/dependency-lookup-context.xml"
        };
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(xmlPath);

        UserHolder res = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(res);
    }
}
