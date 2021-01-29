package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.util.Arrays;

public class PropertiesConfigBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);

        String path = "META-INF/user.properties";
        Resource resource = new ClassPathResource(path);
        //  处理中文乱码
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");

        reader.loadBeanDefinitions(encodedResource);
        //  User{id=1, name='黄壮壮', beanName='user'}
        System.out.println(beanFactory.getBean("user"));
    }
}
