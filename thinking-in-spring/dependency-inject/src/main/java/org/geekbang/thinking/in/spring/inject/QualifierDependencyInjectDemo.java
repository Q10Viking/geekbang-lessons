package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class QualifierDependencyInjectDemo {

    @Autowired
    @Qualifier("user")
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(QualifierDependencyInjectDemo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        QualifierDependencyInjectDemo bean = applicationContext.getBean(QualifierDependencyInjectDemo.class);
        System.out.println(bean.user);


        applicationContext.close();

    }
}
