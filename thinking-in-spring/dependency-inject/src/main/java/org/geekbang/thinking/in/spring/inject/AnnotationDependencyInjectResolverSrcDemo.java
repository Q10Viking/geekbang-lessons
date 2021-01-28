package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationDependencyInjectResolverSrcDemo {

    @MyAutowired
    private User user;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(AnnotationDependencyInjectResolverSrcDemo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        AnnotationDependencyInjectResolverSrcDemo bean = applicationContext.getBean(AnnotationDependencyInjectResolverSrcDemo.class);
        System.out.println(bean.user);

        applicationContext.close();

    }
}
