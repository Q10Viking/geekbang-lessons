package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class RegisterUserBeanDefinition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id",2L)
                .addPropertyValue("name","Q10Viking");

        BeanDefinitionRegistry t = (BeanDefinitionRegistry)context;
        // 带名称的注册
        t.registerBeanDefinition("user1",beanDefinitionBuilder.getBeanDefinition());
        //  使用spring默认命名机制注册
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),context);

        context.refresh();
        Map<String, User> users = context.getBeansOfType(User.class);

        System.out.println(users);
    }


}
