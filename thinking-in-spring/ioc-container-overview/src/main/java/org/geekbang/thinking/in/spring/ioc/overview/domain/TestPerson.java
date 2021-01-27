package org.geekbang.thinking.in.spring.ioc.overview.domain;

public class TestPerson {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestPerson{" +
                "name='" + name + '\'' +
                '}';
    }
}
