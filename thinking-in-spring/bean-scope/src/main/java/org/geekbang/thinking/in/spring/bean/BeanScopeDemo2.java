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

public class BeanScopeDemo2 {


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
        applicationContext.register(BeanScopeDemo2.class);

        applicationContext.refresh();
        beanScopeByLookUp(applicationContext);
        applicationContext.close();
    }
    //  依赖查询bean
    private static void beanScopeByLookUp(ApplicationContext applicationContext){
        for(int i=0;i<3;i++){
            User singleUser = applicationContext.getBean("singleUser", User.class);
            System.out.println("singletonUser : "+singleUser);

            User prototypeUser = applicationContext.getBean("prototypeUser", User.class);
            System.out.println("prototypeUser : "+prototypeUser);
        }
    }
}
/**
 singletonUser : User{id=139915200922155,  beanName='singleUser'}
 prototypeUser : User{id=139915222536501,  beanName='prototypeUser'}
 singletonUser : User{id=139915200922155,  beanName='singleUser'}
 prototypeUser : User{id=139915223224269,  beanName='prototypeUser'}
 singletonUser : User{id=139915200922155,  beanName='singleUser'}
 prototypeUser : User{id=139915223495109,  beanName='prototypeUser'}
 */