package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

public class AwareCallbackDemo2 {
    private static final String[] xmlPath = {
            "META-INF/bean-constructor-dependency-inject.xml",
            "META-INF/dependency-lookup-context.xml"
    };
    public static void main(String[] args) {
//        executeBeanAwareCallBack();
        System.out.println("--------------------------------");
        executeApplicationContextAwareCallBack();
    }

    public static void executeBeanAwareCallBack(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(xmlPath);

        UserHolder res = beanFactory.getBean("userHolder",UserHolder.class);
        System.out.println(res);
    }

    public static void executeApplicationContextAwareCallBack(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        context.setConfigLocations(xmlPath);

        context.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        });


        context.refresh();
        UserHolder res = context.getBean("userHolder",UserHolder.class);
        System.out.println(res);

        context.close();
    }


    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor{

        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(beanClass)) {
                System.out.println("postProcessBeforeInstantiation(Class<?> beanClass, String beanName)被调用");
            }
            return null;
        }

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())) {
                System.out.println("postProcessBeforeInitialization(Object bean, String beanName)被调用");
            }
            return null;
        }
    }

}
/**
 --------------------------------
 postProcessBeforeInstantiation(Class<?> beanClass, String beanName)被调用
 postProcessBeforeInitialization(Object bean, String beanName)被调用
 UserHolder{user=SuperUser{address='杭州'} User{id=1, name='黄壮壮', beanName='superUser'}, number=100, version='V1.0', beanName='userHolder'}
 */