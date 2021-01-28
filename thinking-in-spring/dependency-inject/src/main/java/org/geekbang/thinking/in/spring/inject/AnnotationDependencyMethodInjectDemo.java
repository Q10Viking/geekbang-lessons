package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class AnnotationDependencyMethodInjectDemo {

    private UserHolder userHolder1;
    private UserHolder userHolder2;
    private User user;

    @Autowired
    public void initUserHolder1(UserHolder userHolder){ this.userHolder1 = userHolder; }

    @Resource
    public void initUserHolder2(UserHolder userHolder){ this.userHolder2 = userHolder; }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

    @Bean
    public void user1(User user){ this.user = user;}


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册本类的Bean   Configuration 也会注入到容器中
        applicationContext.register(AnnotationDependencyMethodInjectDemo.class);

        // 加载 user bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        //  Configuration配置的类也会成为容器的bean
        AnnotationDependencyMethodInjectDemo bean = applicationContext.getBean(AnnotationDependencyMethodInjectDemo.class);
        System.out.println(bean.userHolder1);
        System.out.println(bean.userHolder1 == bean.userHolder2);    // true
        System.out.println(bean.user);

        applicationContext.close();
    }

}
