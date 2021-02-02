package org.geekbang.thinking.in.spring.configuration.meta;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanAnnotationCfgDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanAnnotationCfgDemo.class);
        applicationContext.refresh();
    }

    @Bean
    public User user(){
        System.out.println("我被执行了");
        User user = new User();
        user.setId(3L);
        user.setName("Q10Viking");
        return user;
    }
}
/**
 我被执行了
 */