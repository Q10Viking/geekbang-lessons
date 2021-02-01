package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.util.ObjectUtils;

public class BeanInstantiationCycleDemo9 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  添加自定义的BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        //  添加CommonAnnotationBeanPostProcessor 解决 @PostConstructor
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        //  添加MyDestructorAwareBeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyDestructorAwareBeanPostProcessor());

        String[] xmlPath = {
                "META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-inject.xml"
        };

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(xmlPath);
        //  SmartInitializingSingleton 通常在 Spring ApplicationContext场景中使用
        //  preInstantiateSingletons 将已注册的beanDefinition 初始化为 Spring bean
        beanFactory.preInstantiateSingletons();

        UserHolder userHolder = beanFactory.getBean("userHolder",UserHolder.class);
        //  Destroy the given bean instance 只是在容器中销毁，并没有被GC掉
        beanFactory.destroyBean("userHolder",userHolder);
        System.out.println(userHolder);
    }

    //  初始化的相关回调接口
    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

        //  属性填充前回调
        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                //  假设 配置了 <property name="number" value="100"/>的话，PropertyValues 则包含一个PropertyValue(number=100)
                final MutablePropertyValues propertyValues;
                final String version = "V2.0";
                if(pvs instanceof MutablePropertyValues){
                    propertyValues = (MutablePropertyValues) pvs;
                }else{
                    propertyValues = new MutablePropertyValues();
                }

                if(propertyValues.contains("version")){
                    TypedStringValue versionValue = (TypedStringValue)propertyValues.getPropertyValue("version").getValue();
                    propertyValues.removePropertyValue("version");

                    propertyValues.addPropertyValue("version",version);
                    System.out.println("属性填充前 postProcessProperties: " + versionValue.getValue()+" =》 " + version);
                }
                return propertyValues;
            }
            return null;
        }

        //  初始化前阶段
        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                UserHolder userHolder = UserHolder.class.cast(bean);
                String preVersion = userHolder.getVersion();
                String nextVersion = "V3.0";
                userHolder.setVersion(nextVersion);
                System.out.println("初始化前阶段 postProcessBeforeInitialization: "+preVersion + " => " + nextVersion);
            }
            return bean;
        }


        //  初始化后阶段
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                UserHolder userHolder = UserHolder.class.cast(bean);
                String preVersion = userHolder.getVersion();
                String nextVersion = "V7.0";
                userHolder.setVersion(nextVersion);
                System.out.println("初始化后阶段 postProcessAfterInstantiation: "+preVersion + " => " + nextVersion);
            }
            return bean;
        }
    }


    //  销毁的相关回调接口
    static class MyDestructorAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor{
        @Override
        public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {

            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                UserHolder userHolder = UserHolder.class.cast(bean);
                String preVersion = userHolder.getVersion();
                String nextVersion = "V9.0";
                userHolder.setVersion(nextVersion);
                System.out.println("销毁前阶段 postProcessAfterInstantiation: "+preVersion + " => " + nextVersion);
            }
        }
    }
}
/**
 属性填充前 postProcessProperties: V1.0 =》 V2.0
 初始化前阶段 postProcessBeforeInitialization: V2.0 => V3.0
 初始化 postConstruct: V3.0 => V4.0
 初始化 afterPropertiesSet: V4.0 => V5.0
 初始化 doInit: V5.0 => V6.0
 初始化后阶段 postProcessAfterInstantiation: V6.0 => V7.0
 初始化完成 afterSingletonsInstantiated: V7.0 => V8.0
 销毁前阶段 postProcessAfterInstantiation: V8.0 => V9.0
 UserHolder{user=SuperUser{address='杭州'} User{id=1, name='黄壮壮', beanName='superUser'}, number=100, version='V9.0', beanName='userHolder'}
 */