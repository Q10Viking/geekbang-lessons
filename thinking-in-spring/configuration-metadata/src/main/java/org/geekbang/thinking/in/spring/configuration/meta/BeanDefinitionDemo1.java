package org.geekbang.thinking.in.spring.configuration.meta;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ObjectUtils;

public class BeanDefinitionDemo1 {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name","Q10Viking");

        //  附加属性    （辅助） 存储在 继承的 AttributeAccessorSupport 属性
        //  Map<String, Object> attributes = new LinkedHashMap<>(); 中
        AbstractBeanDefinition bd = beanDefinitionBuilder.getBeanDefinition();
        bd.setAttribute("name","changed-Q10Viking");

        //  设置BeanDefinition 来源
        bd.setSource(BeanDefinitionDemo1.class);


        //  初始化后阶段修改name 为额外存储的属性值即 name=Q10Viking 变成 name=changed-Q10Viking
        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if(ObjectUtils.nullSafeEquals("user",beanName) && User.class.equals(bean.getClass())){
                    BeanDefinition userdb = beanFactory.getBeanDefinition("user");
                    //  如果来自BeanDefinitionDemo1.class 做一个定制，即修改属性
                    if(BeanDefinitionDemo1.class.equals(userdb.getSource())){
                        System.out.println("定制操作");
                        String attr = (String)userdb.getAttribute("name");  //    attr =  "changed-Q10Viking"
                        User user = (User)bean;
                        ((User) bean).setName(attr);
                    }
                }
                return bean;
            }
        });

        //  注册bean definition
        beanFactory.registerBeanDefinition("user",bd);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user.getName());
    }
}
/**
 定制操作
 changed-Q10Viking
 */