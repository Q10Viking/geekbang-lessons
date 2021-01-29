package org.geekbang.thinking.in.spring.bean;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ThreadLocalScope implements Scope {

    public static final String SCOPE_NAME = "thread-scope";

    private final NamedThreadLocal<Map<String,Object>> threadLocal = new NamedThreadLocal<Map<String,Object>>("thread-local-scope"){
        //  确保threadLocal.get 不会返回null
        @Override
        protected Map<String,Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object obj = context.get(name);

        if(obj == null){
            obj = objectFactory.getObject();
            context.put(name,obj);
        }

        return obj;
    }

    private Map<String,Object> getContext(){
        Map<String, Object> context = threadLocal.get();
        return context;
    }

    @Override
    public Object remove(String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    //  通过线程Id来确定是不是同一个作用域，与sessionScope中的jsessionid一样
    @Override
    public String getConversationId() {
        System.out.println("什么时候被调用");
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
