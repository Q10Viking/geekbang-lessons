package org.geekbang.thinking.in.spring.configuration.meta;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        //  将user元素注册对应BeanDefinitionParser
        registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}

