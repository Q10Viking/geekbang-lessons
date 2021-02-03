package org.geekbang.thinking.in.spring.configuration.meta;


import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

public class YmlCfgDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/yml-user-context.xml");

        Map<String, Object> object = beanFactory.getBean("ymlCfg",Map.class);
        System.out.println(object);
    }
}
