package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

//  注册Config这个Bean
@Import(AnnotationBeanRegisterDemo.Config.class)
public class AnnotationBeanRegisterDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //  注册需要处理的组件类
        context.register(AnnotationBeanRegisterDemo.class);
        context.refresh();

        Config config = context.getBean(Config.class);
        User user = context.getBean(User.class);
        System.out.println(user);
        System.out.println(config);

        context.close();
    }
    @Component
    public static class Config{
        //  注册这个Bean
        @Bean
        public User user(){
            User user = new User();
            user.setId(2L);
            user.setName("Q10Viking");
            return user;
        }
    }
}
