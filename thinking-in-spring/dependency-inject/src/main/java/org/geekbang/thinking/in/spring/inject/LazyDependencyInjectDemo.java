package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Set;

public class LazyDependencyInjectDemo {

    @Autowired
    private List<User> realTimeUsers;   //  实时注入

    @Autowired
    private ObjectProvider<User> objectProvider;    //  延迟注入，推荐，包含集合与单一

    @Autowired
    private ObjectProvider<Set<User>> objectProviderSet;

    @Autowired
    private ObjectFactory<User> objectFactorySingle;  //  延迟注入  单一

    @Autowired
    private ObjectFactory<Set<User>> objectFactorySet;  //  延迟注入 集合


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(LazyDependencyInjectDemo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        LazyDependencyInjectDemo bean = applicationContext.getBean(LazyDependencyInjectDemo.class);

        User user1 = bean.objectProvider.getObject();
        //  ObjectProvider 可以获取单个bean
        System.out.println(user1);  // superUser(primary)
        //  也可以获取所有的user类型的bean
        bean.objectProvider.stream().forEach(System.out::println);  //  user,superUser


        Set<User> objectProviderUserSet = bean.objectProviderSet.getObject();
        System.out.println(objectProviderUserSet);  //  user,superUser


        User objectFactorySingle = bean.objectFactorySingle.getObject();
        System.out.println(objectFactorySingle);    // superUser(primary)

        Set<User> object = bean.objectFactorySet.getObject();
        System.out.println(object); // user,superUser

        applicationContext.close();

    }
}
