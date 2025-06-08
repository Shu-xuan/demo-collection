package com.hhy.ajc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect // ⬅️注意此切面并未被 Spring 管理
public class MyAspect {

    @Before("execution(* com.hhy.ajc.service.MyService.*(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println("===== 前置增强: " + joinPoint.getSignature().getName() + " =====");
    }
}
