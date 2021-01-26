package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanInstanceDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantance-context.xml");

        User user1 = beanFactory.getBean("user-by-static-method",User.class);
        User user2 = beanFactory.getBean("user-by-instance-method",User.class);

        System.out.println(user2);
    }
}
