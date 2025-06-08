package com.hhy.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 21:05
 */
@Component
@Aspect
public class MyAspect {
    // 配置切点
    @Pointcut("execution(* com.hhy.spring.service.IMyservice.*(..))")
    public void qiedian(){}

    @Before("qiedian()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==== @Before注解: before ====" + joinPoint.getSignature());
    }

    @After("execution(String com.hhy.spring.service.IMyservice.*(..))")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("==== @After注解: before return ====" + joinPoint.getSignature().getName());
    }

}
