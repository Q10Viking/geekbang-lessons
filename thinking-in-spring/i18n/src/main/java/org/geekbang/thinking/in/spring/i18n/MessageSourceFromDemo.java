package org.geekbang.thinking.in.spring.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageSourceFromDemo {

    public static void main(String[] args) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("test");
        messageSource.setDefaultEncoding("utf-8");
        System.out.println(messageSource.getMessage("greeting",new Object[]{"Q10Viking"}, Locale.CHINESE));


    }
}
/**
 1 您好 Q10Viking
 */