package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserBean;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitialazationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanInitialazationDemo.class);
        context.refresh();
        context.getBean(UserFactory.class);

        context.close();
    }

    @Bean(initMethod = "initializeMethod")
    @Lazy
    public UserFactory userFactory(){
        return new DefaultUserBean();
    }


}
