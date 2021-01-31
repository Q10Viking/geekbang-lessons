package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Arrays;

public class MergeConfigBeanDefinitionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String path = "META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(path);
        beanFactory.getBean("user");
        //  在获取Bean的时候，进行merge
        System.out.println(beanFactory.getBean("superUser"));
        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.printf("bean 的数量 %d\n",beanFactory.getBeanDefinitionCount());
    }
}
