package org.geekbang.thinking.in.spring.resource.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;


public interface ResourceUtils {
    final static  String DEFAULT_ENCODE = "utf-8";

    static String getContent(Resource resource){
        return getContent(resource,DEFAULT_ENCODE);
    }
    //  获取Reader的内容为字符串
    static  String getContent(Resource resource, String encoding)  {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        try(Reader reader = encodedResource.getReader()){
            return IOUtils.toString(reader);
        }catch (IOException e){
            throw new RuntimeException("资源解析失败");
        }
    }
}
