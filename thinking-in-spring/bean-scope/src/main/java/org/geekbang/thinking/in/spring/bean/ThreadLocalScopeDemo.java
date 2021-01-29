package org.geekbang.thinking.in.spring.bean;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Arrays;
import java.util.Random;

public class ThreadLocalScopeDemo {

    //  注册bean到自定义的作用域中
    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    private User user(){
        User user = new User();
        try{
            Thread.sleep(1000);
            user.setId((System.nanoTime()));
        }catch (Exception e){}
        finally {
            return user;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);
        //  注册自定义作用域
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME,new ThreadLocalScope());
        });

        applicationContext.refresh();
        System.out.printf("容器中注入的Bean: %d%n",applicationContext.getBeanDefinitionCount());
        lookup(applicationContext);
        while(true){
            Thread.sleep(1000);
            System.out.printf("容器中注入的Bean: %d%n",applicationContext.getBeanDefinitionCount());
            System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        }
//        applicationContext.close();
    }

    private static void lookup(AnnotationConfigApplicationContext context) {
        for (int i=0;i<3;i++){
            Thread thread = new Thread(()->{
                User user1 = context.getBean("user", User.class);
                System.out.printf("[ Thread id : %d ] user = %s%n",Thread.currentThread().getId(),user1);
                User user2 = context.getBean("user", User.class);
                System.out.printf("[ Thread id : %d ] user = %s%n",Thread.currentThread().getId(),user2);
            });

            thread.start();
        }
    }
}
/**
 容器中注入的Bean: 7
 容器中注入的Bean: 7
 User Bean [user] 初始化...
 User Bean [user] 初始化...
 [ Thread id : 14 ] user = User{id=170617962737675, name='null', beanName='user'}
 [ Thread id : 14 ] user = User{id=170617962737675, name='null', beanName='user'}
 User Bean [user] 初始化...
 [ Thread id : 15 ] user = User{id=170617962711704, name='null', beanName='user'}
 [ Thread id : 15 ] user = User{id=170617962711704, name='null', beanName='user'}
 [ Thread id : 13 ] user = User{id=170617962737675, name='null', beanName='user'}
 [ Thread id : 13 ] user = User{id=170617962737675, name='null', beanName='user'}
 容器中注入的Bean: 7
 容器中注入的Bean: 7
 */