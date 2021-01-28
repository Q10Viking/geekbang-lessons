package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.inject.annotation.InjectUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class AnnotationDependencyInjectResolverSrc2Demo {

    @Autowired
    private User user1;

    @InjectUser
    private User user2;

    public AnnotationDependencyInjectResolverSrc2Demo(){
        System.out.println("AnnotationDependencyInjectResolverSrc2Demo被调用了");
    }

//    //  static 提前注册该bean，而不是该类实例化时
//    @Bean(name = AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        System.out.println("beanPostProcessor被调用了");
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        //  兼容以前的注解
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(
//                Autowired.class, Value.class,InjectUser.class
//        ));
//
//        processor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return processor;
//    }

//    @Bean(name = "myAutowiredAnnotationBeanPostProcessor")
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        //  兼容以前的注解
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(Arrays.asList(
//                Autowired.class, Value.class,InjectUser.class
//        ));
//
//        processor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
//        return processor;
//    }

    //  2
//    @Bean(name = "myAutowiredAnnotationBeanPostProcessor")
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
//        processor.setAutowiredAnnotationType(InjectUser.class);
//        return processor;
//    }
//
    @Bean(name = "myAutowiredAnnotationBeanPostProcessor")
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor(){
        System.out.println("beanPostProcessor被调用了");
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        processor.setAutowiredAnnotationType(InjectUser.class);
        return processor;
    }




    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(AnnotationDependencyInjectResolverSrc2Demo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        AnnotationDependencyInjectResolverSrc2Demo bean = applicationContext.getBean(AnnotationDependencyInjectResolverSrc2Demo.class);
        System.out.println(bean.user1);
        System.out.println(bean.user2);

        long count = applicationContext.getBeanProvider(AutowiredAnnotationBeanPostProcessor.class).stream().count();
        System.out.println(count);

        applicationContext.close();

    }
}
