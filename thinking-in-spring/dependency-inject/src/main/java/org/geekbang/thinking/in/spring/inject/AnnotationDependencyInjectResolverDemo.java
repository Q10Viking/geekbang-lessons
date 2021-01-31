package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.*;

public class AnnotationDependencyInjectResolverDemo {

    /**
     * DependencyDescriptor ->
     * 必须（required = true）
     * 实时注入（eager = true)
     * 通过类型（User.class）
     * 字段名称（"user"）
     * 是否首要（primary = true）
     */
//    @Autowired
//    private User user;  //  依赖查找处理      //  superUser(primary)

    //  依赖注入过程中支持的类型

//    @Autowired
//    private Map<String ,User> map;  //  {user=user,superUser=superUser}

//    @Autowired
//    private Collection<User> collections;
//
//    @Autowired
//    private Optional<User> optionalUser;    //  optional(superUser)

    @Autowired
    @Lazy
    private User lazyUser;




    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //  注册该类
        applicationContext.register(AnnotationDependencyInjectResolverDemo.class);

        // 加载user userHolder bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);

        applicationContext.refresh();

        AnnotationDependencyInjectResolverDemo bean = applicationContext.getBean(AnnotationDependencyInjectResolverDemo.class);
        System.out.println(bean.lazyUser);
//        System.out.println(bean.map);
//        System.out.println(bean.optionalUser);

        applicationContext.close();

    }
}
