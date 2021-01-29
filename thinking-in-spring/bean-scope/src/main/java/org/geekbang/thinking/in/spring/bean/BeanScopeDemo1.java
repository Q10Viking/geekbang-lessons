package org.geekbang.thinking.in.spring.bean;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo1 {

    @Autowired
    @Qualifier("singleUser")
    private User singletonUser1;

    @Autowired
    @Qualifier("singleUser")
    private User singletonUser2;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser1;

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser2;

    @Autowired
    private Map<String,User> usersMapping;

    @Bean   // 默认bean scope 为single
    private User singleUser(){
        return createUser();
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private User prototypeUser(){
        return createUser();
    }


    private User createUser(){
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo1.class);

        applicationContext.refresh();
        applicationContext.close();
    }
}
/**
 singletonUser1: User{id=140710454851077, beanName='singleUser'}
 singletonUser2: User{id=140710454851077, beanName='singleUser'}
 prototypeUser1: User{id=140710459718780, beanName='prototypeUser'}
 prototypeUser2: User{id=140710460427881, beanName='prototypeUser'}
 */