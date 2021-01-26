package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {
    default User createUser(){
        System.out.println("User Bean 工厂");
        return User.createUser();
    }
}
