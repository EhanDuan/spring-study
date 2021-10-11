package com.yihan.config;

import com.yihan.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration //这个也会被spring容器托管。因为他也属于一个@Component，只不过代表他是一个配置类，和之前看到的beans.xml一样
@ComponentScan("com.yihan.pojo")
@Import(MyConfig2.class)
public class MyConfig {

    // 注册一个bean，就相当于我们之前写的bean标签
    // 这个方法的名字就相当于bean标签中的id实行；
    // 方法的返回值就相当于bean标签的class属性
    @Bean
    public User getUser(){
        return new User();
    }
}
