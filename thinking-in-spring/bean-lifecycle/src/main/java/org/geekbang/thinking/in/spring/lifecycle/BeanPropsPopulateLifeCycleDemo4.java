package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.util.ObjectUtils;

public class BeanPropsPopulateLifeCycleDemo4 {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  添加自定义的BeanPostProcessor
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        String[] xmlPath = {
                "META-INF/dependency-lookup-context.xml",
                "META-INF/bean-constructor-dependency-inject.xml"
        };

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(xmlPath);

        Object res = beanFactory.getBean("userHolder");
        System.out.println(res);


    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        //  bean definition merged 后进行的回调方法
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if(false && ObjectUtils.nullSafeEquals("superUser",beanName) && SuperUser.class.equals(beanClass) ){
                    return new SuperUser();
            }
            return null;
        }

        @Override
        public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
                User user = (User)bean;
                user.setId(3L);
                user.setName("拦截Name");
                //  "user"对象不允许，（配置元信息 -> 属性值）属性填入
                return true;
            }
            return true;
        }

        @Override
        public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
            if(ObjectUtils.nullSafeEquals("userHolder",beanName) && UserHolder.class.equals(bean.getClass())){
                //  假设 配置了 <property name="number" value="100"/>的话，PropertyValues 则包含一个PropertyValue(number=100)
                final MutablePropertyValues propertyValues;

                if(pvs instanceof MutablePropertyValues){
                    propertyValues = (MutablePropertyValues) pvs;
                }else{
                    propertyValues = new MutablePropertyValues();
                }

                if(propertyValues.contains("version")){
                    TypedStringValue version = (TypedStringValue)propertyValues.getPropertyValue("version").getValue();
                    propertyValues.removePropertyValue("version");

                    propertyValues.addPropertyValue("version","V2.0");
                    System.out.println("postProcessProperties: " + version.getValue()+" =》 V2.0");
                }
                //  更改属性为number=200
                propertyValues.addPropertyValue("number",200);

                return propertyValues;
            }
            return null;
        }
    }
}
