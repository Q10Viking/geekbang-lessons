package org.geekbang.thinking.in.spring.denpendency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class DependencySourceDemo {

    //  依赖注入 早于 初始化
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void initBean(){
        System.out.println("beanFactory == applicationContext "+(beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getAutowireCapableBeanFactory() "+(beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("applicationContext == applicationEventPublisher "+ (applicationContext == applicationEventPublisher));
        System.out.println("applicationContext == resourceLoader " + (applicationContext == resourceLoader));
    }

    //  验证ResolverDependency bean不能依赖查询，只能依赖注入
    public static void lookup(ApplicationContext context){
        getBean(context,BeanFactory.class);
        getBean(context,ApplicationContext.class);
        getBean(context,ApplicationEventPublisher.class);
        getBean(context,ResourceLoader.class);
    }

    private static <T> T getBean(ApplicationContext context,Class<T> type){
        try{
            return context.getBean(type);
        }catch (NoSuchBeanDefinitionException e){
            System.out.printf("在当前容器中查找不到 %s \n",type);
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        lookup(applicationContext);

        applicationContext.close();

    }
}
/**
 beanFactory == applicationContext false
 beanFactory == applicationContext.getAutowireCapableBeanFactory() true
 applicationContext == applicationEventPublisher true
 applicationContext == resourceLoader true
 在当前容器中查找不到 interface org.springframework.beans.factory.BeanFactory
 在当前容器中查找不到 interface org.springframework.context.ApplicationContext
 在当前容器中查找不到 interface org.springframework.context.ApplicationEventPublisher
 在当前容器中查找不到 interface org.springframework.core.io.ResourceLoader
 */