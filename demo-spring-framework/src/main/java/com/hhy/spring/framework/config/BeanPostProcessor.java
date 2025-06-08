package com.hhy.spring.framework.config;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public interface BeanPostProcessor {
    default Object postProcessBeforeInitialization(Object bean, String beanName) {return bean;};
    default Object postProcessAfterInitialization(Object bean, String beanName) {return bean;};
}
