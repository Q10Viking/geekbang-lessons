package org.geekbang.thinking.in.spring.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ANnotationConfigBeanDefinitionDemo {

    private String name;

    public ANnotationConfigBeanDefinitionDemo(){ this.name = "天空之城";}

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(beanFactory);

        reader.register(ANnotationConfigBeanDefinitionDemo.class);

        //  验证使用类首字母AnnotationBeanNameGenerator#buildDefaultBeanName默认名称生成规则
        ANnotationConfigBeanDefinitionDemo bean =
        (ANnotationConfigBeanDefinitionDemo)beanFactory.getBean("ANnotationConfigBeanDefinitionDemo");

        System.out.println(bean.name);

    }
}
