package org.geekbang.thinking.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

public class InjectResourceLoaderDemo implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init(){
        System.out.println("resourceLoader == autowiredResourceLoader : "+ (resourceLoader == autowiredResourceLoader));
        System.out.println("resourceLoader == applicationContext : "+(resourceLoader == applicationContext));

        System.out.println(autowiredResourceLoader.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(InjectResourceLoaderDemo.class);

        context.refresh();
        context.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
/**
 resourceLoader == autowiredResourceLoader : true
 resourceLoader == applicationContext : true
 AnnotationConfigApplicationContext
 */