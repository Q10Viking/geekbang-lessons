package org.geekbang.thinking.in.spring.configuration.meta;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration    也可以不需要
@PropertySource(
        name = "properties-file-configuration",
        value = {"META-INF/user-test.properties"},
        encoding = "utf-8"
)
public class PropertiesCfgDemo {

    @Bean
    public User user(@Value("${test.userId}") Long id,@Value("${test.username}")String username){
        User user = new User();
        user.setId(id);
        user.setName(username);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(PropertiesCfgDemo.class);

        context.refresh();

        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();

    }
}
