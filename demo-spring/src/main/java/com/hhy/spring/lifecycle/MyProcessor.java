package com.hhy.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 23:35
 */
public class MyProcessor implements BeanPostProcessor {
    /**
     * 初始化前
     * @param bean
     * @param beanName applicationContext.xml中配置的Bean的名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)) {
            System.out.println("4.初始化前 BeanPostProcessor#before执行了!!!");
        }
        return bean;
    }

    /**
     * 初始化后
     * @param bean
     * @param beanName Bean的名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if("person".equals(beanName)) {
            System.out.println("7.初始化后 BeanPostProcessor#after执行了!!!");
        }
        return bean;
    }
}
