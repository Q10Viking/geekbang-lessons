package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.SuperUser;
import org.geekbang.thinking.in.spring.ioc.overview.domain.TestPerson;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class HierarchicalLookUpDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalLookUpDemo.class);
        //  获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        //  设置 Parent BeanFactory
        HierarchicalBeanFactory parentBeanFactory = createBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);

        displayContainsBean(beanFactory,"user");    // true

        System.out.println(beanFactory.containsBean("user"));   // true
        System.out.println(beanFactory.containsLocalBean("user"));  // false
        BeanFactoryUtils.beanOfType((ListableBeanFactory)parentBeanFactory, TestPerson.class);
        TestPerson user = BeanFactoryUtils.beanOfTypeIncludingAncestors((ListableBeanFactory)parentBeanFactory, TestPerson.class);
        System.out.println(user);
        Map<String, User> stringUserMap = BeanFactoryUtils.beansOfTypeIncludingAncestors(beanFactory, User.class);
        System.out.println(stringUserMap);

    }

    private static ConfigurableBeanFactory createBeanFactory(){
        // 创建 BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        // XML 配置文件 ClassPath 路径
        String path = "classpath:/META-INF/dependency-lookup-context.xml";
        //  加载配置
        reader.loadBeanDefinitions(path);
        return beanFactory;
    }


    private static void displayContainsBean(HierarchicalBeanFactory beanFactory,String beanName){
        System.out.printf("当前 BeanFactory[%s] 是否包含 Bean[name : %s] : %s\n",beanFactory,beanName,containsBean(beanFactory,beanName));
    }

    // 层级依赖查找，递归方式
    private static boolean containsBean(HierarchicalBeanFactory beanFactory,String beanName){
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if(containsBean(parentHierarchicalBeanFactory,beanName)){
                return true;
            }
        }
        //  是否是当前BeanFactory中含有该bean
        return beanFactory.containsLocalBean(beanName);
    }
}
