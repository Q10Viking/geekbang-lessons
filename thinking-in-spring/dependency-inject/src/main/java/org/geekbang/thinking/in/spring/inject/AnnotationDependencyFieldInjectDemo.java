package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class AnnotationDependencyFieldInjectDemo {
    @Autowired
    private
//    static    @Autowired 会忽略static字段
    UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册本类的Bean   Configuration 也会注入到容器中
        applicationContext.register(AnnotationDependencyFieldInjectDemo.class);

        // 加载 user bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        //  Configuration配置的类也会成为容器的bean
        AnnotationDependencyFieldInjectDemo bean = applicationContext.getBean(AnnotationDependencyFieldInjectDemo.class);
        System.out.println(bean.userHolder);
        System.out.println(bean.userHolder == bean.userHolder2);    // true

        applicationContext.close();
    }

    //  在注册userHolder这个Bean的同时，容器注入了user
    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}
