package org.geekbang.thinking.in.spring.configuration.meta;


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

@PropertySource(
        name = "yaml-cfg",
        value = {"META-INF/user-test.yml"},
        factory = YamlPropertySourceFactory.class
)
public class YmlCfgDemo2 {

    @Bean
    public User user(@Value("${test-user.id}") Long id,@Value("${test-user.name}") String name){
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(YmlCfgDemo2.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }
}
/**
 User{id=7, name='yml test', city=null, beanName='user'}
 */