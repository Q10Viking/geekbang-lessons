package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpecialInstanceDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-instantance-context.xml");
        ServiceLoader<UserFactory> userFactorysrv = beanFactory.getBean("special-service-load", ServiceLoader.class);
        display(userFactorysrv);
    }

    private static void display(ServiceLoader<UserFactory> userFactorysrv) {
        Iterator<UserFactory> iterator = userFactorysrv.iterator();

        while(iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }

    //  ServiceLoader使用方式
    public static void getObjectToExpose(){
        ServiceLoader<UserFactory> loader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());
        Iterator<UserFactory> iterator = loader.iterator();

        while(iterator.hasNext()){
            UserFactory next = iterator.next();
            System.out.println(next.createUser());
        }
    }
}
