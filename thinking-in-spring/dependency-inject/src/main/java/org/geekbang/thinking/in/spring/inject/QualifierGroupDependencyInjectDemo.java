package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class QualifierGroupDependencyInjectDemo {

    @Autowired
    private List<User> userList1;   //  user,superUser

    @Autowired
    @Qualifier
    private List<User> userList2;   // user1,user2,user3,user4


    @Autowired
    @UserGroup
    private List<User> userList3;   //  user3,user4

    @Bean
    @Qualifier
    private User user1(){
        return createUser(7L);
    }

    @Bean
    @Qualifier
    private User user2(){
        return createUser(8L);
    }

    @Bean
    @UserGroup
    private User user3(){
        return createUser(9L);
    }

    @Bean
    @UserGroup
    private User user4(){
        return createUser(10L);
    }


    private User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(QualifierGroupDependencyInjectDemo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        QualifierGroupDependencyInjectDemo bean = applicationContext.getBean(QualifierGroupDependencyInjectDemo.class);
        System.out.println(bean.userList1.size());
        System.out.println(bean.userList2.size());
        System.out.println(bean.userList3.size());


        applicationContext.close();

    }
}
