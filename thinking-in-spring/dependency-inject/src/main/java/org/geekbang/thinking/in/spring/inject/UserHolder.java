package org.geekbang.thinking.in.spring.inject;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public class UserHolder {
    private User user;

    public UserHolder(){}

    public UserHolder(User user) {
        System.out.println("构造器注入");
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
