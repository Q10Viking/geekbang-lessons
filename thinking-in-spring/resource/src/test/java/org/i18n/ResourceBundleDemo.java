package org.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceBundleDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        printMsg(Locale.SIMPLIFIED_CHINESE);
        System.out.println("-----------------------");
        printMsg(Locale.ENGLISH);

    }

    private static void  printMsg(Locale locale) throws UnsupportedEncodingException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("test", locale);
        String value = resourceBundle.getString("name");
        String utf8Value = new String (value.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(utf8Value);
    }
}

/**
 黄壮壮
 -----------------------
 huangzhuangzhuang
 */