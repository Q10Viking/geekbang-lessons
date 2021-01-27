package org.geekbang.thinking.in.spring.inject;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class XmlDependencyConstructorInjectDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String beanXmlPath = "classpath:/META-INF/constructor-dependency-inject.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }
}
