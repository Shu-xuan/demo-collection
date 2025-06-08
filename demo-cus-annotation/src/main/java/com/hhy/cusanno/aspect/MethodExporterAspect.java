package com.hhy.cusanno.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/12 21:15
 */
@Aspect // 注明为切面类
@Component // 交给容器管理
public class MethodExporterAspect {
    /**
     * 说明切面的作用范围，任何被 @MethodExporter 修饰的目标方法都将在执行方法前先执行该切面方法
     *
     * @Around 环绕通知，最强大的通知类型，可以控制方法入参、执行、返回结果等各方面细节
     */
    @Around("@annotation(com.hhy.cusanno.annotation.MethodExporter)")
    public Object methodExporter(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("joinPoint 对象的类名: " + joinPoint.getClass().getSimpleName());
        System.out.println("被增强的方法名: " + joinPoint.getSignature().getName());
        long st = new Date().getTime();
        // 执行目标方法，获取方法返回值
        Object proceed = joinPoint.proceed();
        long et = new Date().getTime();
        System.out.println("执行耗时: " + (et - st));
        return proceed;
    }
}
