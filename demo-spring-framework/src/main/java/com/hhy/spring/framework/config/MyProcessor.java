//package com.hhy.spring.framework.config;
//
//import com.hhy.spring.framework.annotation.Component;
//
///**
// * <p>
// * 描述: TODO
// * </p>
// *
// * @Author huhongyuan
// */
//@Component
//public class MyProcessor implements BeanPostProcessor{
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        System.out.println(beanName + "初始化前 BeanPostProcessor#before执行了!!!");
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) {
//        System.out.println(beanName + "初始化后 BeanPostProcessor#after执行了!!!");
//        return bean;
//    }
//}
