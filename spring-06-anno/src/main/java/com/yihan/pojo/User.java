package com.yihan.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//等价于在bean里注册了User这个bean
@Component
public class User {
    @Value("yihan")
    public String name;
}
