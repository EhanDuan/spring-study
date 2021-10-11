package com.yihan.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

// 使用注解方式实现AOP
@Aspect // 标注这个类是个切面
public class AnnotationPointcut {

    @Before("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("======方法执行前========");
    }

    @After("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void after(){
        System.out.println("======方法执行后========");
    }
    //在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
    @Around("execution(* com.yihan.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("======方法执行环绕前========");

        // 执行方法
        Object proceed = joinPoint.proceed();

        System.out.println("======方法执行环绕后========");
    }
}
