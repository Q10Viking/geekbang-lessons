package org.geekbang.thinking.in.spring.configuration.meta;


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

@ImportResource("META-INF/dependency-lookup-context.xml")
@Import(User.class) //  虽然User类并没有表明@Configuration 也可以注册为bean
@PropertySource(value = "classpath:/META-INF/user-test.properties",encoding = "utf-8")  //  java8+ 支持repeatable(重复配置)
@PropertySource(value = "classpath:/META-INF/user-test.properties",encoding = "utf-8")
public class AnnotatedSpringIocContainerConfigurationDemo2 {

    @Bean
    public User configureUser(@Value("${test.userId}") Long id,@Value("${test.username}") String username){
        User user = new User();
        user.setId(id);
        user.setName(username);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedSpringIocContainerConfigurationDemo2.class);
        context.refresh();

        Map<String, User> beansMap = context.getBeansOfType(User.class);
        beansMap.forEach(AnnotatedSpringIocContainerConfigurationDemo2::printMap);

        context.close();
    }

    public static void printMap(Object key,Object value){
        System.out.println(key + " = " + value);
    }
}
/**
 org.geekbang.thinking.in.spring.ioc.overview.domain.User = User{id=null, name='null', beanName='org.geekbang.thinking.in.spring.ioc.overview.domain.User'}
 configureUser = User{id=6, name='"测试"', beanName='configureUser'}
 user = User{id=1, name='黄壮壮', beanName='user'}
 superUser = SuperUser{address='杭州'} User{id=1, name='黄壮壮', beanName='superUser'}
 */