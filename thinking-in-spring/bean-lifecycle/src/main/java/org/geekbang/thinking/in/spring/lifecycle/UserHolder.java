package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

public class UserHolder implements BeanFactoryAware, BeanClassLoaderAware, BeanNameAware , ApplicationContextAware, EnvironmentAware
        , InitializingBean,SmartInitializingSingleton {
    private final User user;
    private Integer number;
    private String version;
    private BeanFactory beanFactory;
    private ClassLoader classLoader;
    private String beanName;
    private Environment environment;
    private ApplicationContext applicationContext;

    public UserHolder(User user12) {
        this.user = user12;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", version='" + version + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    public void postConstruct(){
        final String nextVersion = "V4.0";
        String prevVersion = this.version;
        setVersion(nextVersion);

        System.out.println("初始化 postConstruct: " + prevVersion + " => " + nextVersion);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        final String nextVersion = "V5.0";
        String prevVersion = this.version;
        setVersion(nextVersion);

        System.out.println("初始化 afterPropertiesSet: " + prevVersion + " => " + nextVersion);
    }


    public void doInit(){
        final String nextVersion = "V6.0";
        String prevVersion = this.version;
        setVersion(nextVersion);

        System.out.println("初始化 doInit: " + prevVersion + " => " + nextVersion);
    }

    @Override
    public void afterSingletonsInstantiated() {
        final String nextVersion = "V8.0";
        String prevVersion = this.version;
        setVersion(nextVersion);

        System.out.println("初始化完成 afterSingletonsInstantiated: " + prevVersion + " => " + nextVersion);
    }
}
