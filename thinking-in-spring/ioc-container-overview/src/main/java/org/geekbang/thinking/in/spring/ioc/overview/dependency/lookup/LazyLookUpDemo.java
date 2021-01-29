package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;

public class LazyLookUpDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(LazyLookUpDemo.class);
        context.refresh();

        context.close();
    }

    @Bean
    public User user(){
        return new User();
    }
}
