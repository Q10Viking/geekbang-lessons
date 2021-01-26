package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserBean implements UserFactory, InitializingBean, DisposableBean {

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

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy: Bean 销毁");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#destroy: Bean 销毁");
    }

    public void doDestroy(){
        System.out.println("@Bean方式销毁");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory 正在被垃圾回收");
    }
}
