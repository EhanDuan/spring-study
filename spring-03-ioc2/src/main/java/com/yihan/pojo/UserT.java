package com.yihan.pojo;

public class UserT {
    private String name;

    public UserT() {
        System.out.println("UserT 无参构造，被创建了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name=" + name);
    }
}
