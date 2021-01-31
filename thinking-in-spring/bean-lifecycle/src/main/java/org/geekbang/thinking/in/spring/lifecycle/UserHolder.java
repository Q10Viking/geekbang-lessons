package org.geekbang.thinking.in.spring.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public class UserHolder {
    private final User user;
    private Integer number;
    private String version;

    public UserHolder(User user12) {
        this.user = user12;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", version='" + version + '\'' +
                '}';
    }
}
