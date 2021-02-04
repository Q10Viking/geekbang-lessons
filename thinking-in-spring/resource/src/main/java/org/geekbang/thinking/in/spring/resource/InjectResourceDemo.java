package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class InjectResourceDemo {

    //  注意不需要加{}
    //  读取单个文件不能加*号，会报错
    @Value("classpath:/META-INF/test-user.properties")
    private Resource resource;

    //  如果要加*号，则使用数组接收 表示注入多个Resource
    @Value("classpath*:/META-INF/test-user.properties")
    private Resource[] resources;

    //  不支持Map方式（没有相应的策略）
//    @Value("classpath*:/META-INF/test-user.properties")
//    private Map<Object,Object> resourceMap;

    @Value("classpath*:/META-INF/test-user.properties")
    private List<Resource> resourcesList;

    @PostConstruct
    public void init(){
        System.out.println(ResourceUtils.getContent(resource));
        System.out.println("---------------------------------");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectResourceDemo.class);
        context.refresh();
        context.close();
    }
}
