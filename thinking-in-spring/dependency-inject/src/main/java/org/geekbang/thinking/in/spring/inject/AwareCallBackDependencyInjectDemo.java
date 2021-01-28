package org.geekbang.thinking.in.spring.inject;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AwareCallBackDependencyInjectDemo implements BeanFactoryAware, ApplicationContextAware {

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareCallBackDependencyInjectDemo.class);

        context.refresh();

        AwareCallBackDependencyInjectDemo demo = context.getBean(AwareCallBackDependencyInjectDemo.class);
        System.out.println(demo.applicationContext == context);     //  true
        System.out.println(demo.beanFactory == context.getBeanFactory());   //  ture

        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
