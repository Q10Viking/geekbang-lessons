package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class AnnotationDependencyConstructorInjectDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册本类的Bean
        applicationContext.register(AnnotationDependencyConstructorInjectDemo.class);
        // 加载 user bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        applicationContext.close();
    }

    //  在注册userHolder这个Bean的同时，容器注入了user
    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }
}
