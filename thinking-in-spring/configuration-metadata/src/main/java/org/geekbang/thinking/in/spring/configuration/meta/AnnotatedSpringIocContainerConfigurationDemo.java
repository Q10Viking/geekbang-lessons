package org.geekbang.thinking.in.spring.configuration.meta;


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

@ImportResource("META-INF/dependency-lookup-context.xml")
@Import(User.class) //  虽然User类并没有表明@Configuration 也可以注册为bean
public class AnnotatedSpringIocContainerConfigurationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotatedSpringIocContainerConfigurationDemo.class);
        context.refresh();

        Map<String, User> beansMap = context.getBeansOfType(User.class);
        beansMap.forEach(AnnotatedSpringIocContainerConfigurationDemo::printMap);

        context.close();
    }

    public static void printMap(Object key,Object value){
        System.out.println(key + " = " + value);
    }
}
/**
 org.geekbang.thinking.in.spring.ioc.overview.domain.User = User{id=null, name='null', beanName='org.geekbang.thinking.in.spring.ioc.overview.domain.User'}
 user = User{id=1, name='黄壮壮', beanName='user'}
 superUser = SuperUser{address='杭州'} User{id=1, name='黄壮壮', beanName='superUser'}
 */
