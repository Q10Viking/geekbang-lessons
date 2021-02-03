package org.geekbang.thinking.in.spring.configuration.meta;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ExtensibleXmlAuthoringDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/user-context.xml");

        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
/**
 User{id=7, name='XMl-USER', city=BEIJING, beanName='7'}
 */