package org.geekbang.thinking.in.spring.bean;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Map;

public class BeanScopeDemo3 implements DisposableBean {

    @Autowired
    @Qualifier("prototypeUser")
    private User prototypeUser;

    @Autowired
    @Qualifier("singletonUser")
    private User singletonUser;

    @Autowired
    private Map<String,User> usersMap;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    private User singletonUser(){
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
        applicationContext.register(BeanScopeDemo3.class);

        applicationContext.refresh();
        applicationContext.close();
    }

    @Override
    public void destroy() throws Exception {
        //  手动调用进行destroy
        prototypeUser.destroy();
        for (Map.Entry<String,User> entry: usersMap.entrySet()){
            BeanDefinition userBD = beanFactory.getBeanDefinition(entry.getKey());
            if(userBD.isPrototype()){
                entry.getValue().destroy();
            }
        }
    }
}
/**
 User Bean [prototypeUser] 初始化...
 User Bean [singletonUser] 初始化...
 User Bean [prototypeUser] 初始化...
 User Bean [prototypeUser] 销毁中...
 User Bean [prototypeUser] 销毁中...
 User Bean [singletonUser] 销毁中...
 */