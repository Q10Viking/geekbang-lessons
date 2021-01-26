package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserBean implements UserFactory, InitializingBean {

    @PostConstruct
    public void init(){
        System.out.println("@PostConstruct: UserFactory初始化");
    }

    public void initializeMethod(){
        System.out.println("@Bean方式初始化");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现InitializingBean");
    }
}
