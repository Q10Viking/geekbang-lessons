package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class SafetyLookupDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SafetyLookupDemo.class);
        applicationContext.refresh();
        //  演示BeanFactory#getBean   不安全
        showBeanFactoryGetBean(applicationContext);
        //  演示ObjectFactory#getObject   不安全
        showObjectFactoryGetObject(applicationContext);
        //  演示ObjectProvider#getIfAvailable 安全
        showObjectProviderGetIfAvailable(applicationContext);
        // 演示ListableBeanFactory#getBeansOfType 安全
        showListableBeanFactoryGetBeansOfType(applicationContext);
        //  演示ObjectProvider#stream
        showObjectProviderStreamOpts(applicationContext);

        Environment environment = applicationContext.getBean("environment", Environment.class);
        System.out.println(environment);

        applicationContext.close();
    }

    public static void showBeanFactoryGetBean(BeanFactory beanFactory){
        printBeansException("BeanFactory#getBean",()-> beanFactory.getBean(User.class));
    }

    public static void showObjectFactoryGetObject(BeanFactory beanFactory){
        ObjectFactory<User> objectFactory = beanFactory.getBeanProvider(User.class);
        printBeansException("ObjectFactory#getObject",()-> objectFactory.getObject());
    }

    public static void showObjectProviderGetIfAvailable(BeanFactory beanFactory){
        ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("ObjectProvider#getIfAvailable",() -> beanProvider.getIfAvailable());
    }

    public static void showListableBeanFactoryGetBeansOfType(BeanFactory beanFactory){
        ListableBeanFactory listableBeanFactory = ListableBeanFactory.class.cast(beanFactory);
        printBeansException("ListableBeanFactory#getBeansOfType",() -> listableBeanFactory.getBeansOfType(User.class));

    }

    public static void showObjectProviderStreamOpts(BeanFactory beanFactory){
        ObjectProvider<User> beanProvider = beanFactory.getBeanProvider(User.class);
        printBeansException("ObjectProvider#stream",() -> beanProvider.stream().forEach(System.out::println));
    }

    private static void printBeansException(String source,Runnable runnable){
        System.err.println("----------------------------------------");
        System.err.println("Source From " + source);
        try{
            runnable.run();
        }catch (BeansException e){
            e.printStackTrace();
        }
    }
}
