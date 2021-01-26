package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserBean;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanGC {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanGC.class);

        context.refresh();
//        context.getBean(UserFactory.class);

        context.close();
        Thread.sleep(5000L);
        System.gc();
        Thread.sleep(5000L);
    }

    @Bean(destroyMethod = "doDestroy")
    public UserFactory userFactory(){
        return new DefaultUserBean();
    }
}
