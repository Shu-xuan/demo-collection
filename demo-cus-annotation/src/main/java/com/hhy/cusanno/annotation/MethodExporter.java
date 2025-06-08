package com.hhy.cusanno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 描述: 自定义注解
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/12 21:11
 */
@Target(ElementType.METHOD) // 标注为方法注解
@Retention(RetentionPolicy.RUNTIME) // 保留策略，此处选择: 运行时
public @interface MethodExporter {
}
