package org.geekbang.thinking.in.spring.inject;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class ApiDependencySetterInjectDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 加载user bean配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        String beanXmlPath = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(beanXmlPath);
        //  注册userHolder bean
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();
        beanFactory.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
    }

    private static BeanDefinition createUserHolderBeanDefinition(){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);
        // api 底层方式的增加依赖注入关系，将user依赖注入到userHolder中
        beanDefinitionBuilder.addPropertyReference("user","superUser");
        return beanDefinitionBuilder.getBeanDefinition();
    }

}
