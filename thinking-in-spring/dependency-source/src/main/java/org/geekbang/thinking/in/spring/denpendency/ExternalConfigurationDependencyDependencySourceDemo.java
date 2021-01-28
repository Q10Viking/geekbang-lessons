package org.geekbang.thinking.in.spring.denpendency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

//  外挂资源配置
@Configuration  //  需要写上这个配置
@PropertySource(value= "classpath:/META-INF/user.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencyDependencySourceDemo {

    @Value("${name:未定义}")
    private String name;

    @Value("${songs:无歌曲}")
    private String songs;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencyDependencySourceDemo.class);

        applicationContext.refresh();

        ExternalConfigurationDependencyDependencySourceDemo bean = applicationContext.getBean(ExternalConfigurationDependencyDependencySourceDemo.class);
        System.out.println(bean.name+"在唱 "+ bean.songs);

        applicationContext.close();

    }
}