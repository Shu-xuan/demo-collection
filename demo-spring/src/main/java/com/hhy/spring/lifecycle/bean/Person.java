package com.hhy.spring.lifecycle.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 23:16
 */
public class Person implements BeanNameAware, InitializingBean, DisposableBean {
    private String name;

    public void init() {
        System.out.println("6.初始化");
    }

    public void myDestroy() {
        System.out.println("10.销毁bean");
    }


    public Person() {
        System.out.println("1.实例化");
    }

    public Person(String name) {
        System.out.println("1.实例化");
        this.name = name;
    }

    public String getName() {
        System.out.println("8.使用bean");
        return name;
    }

    public void setName(String name) {
        System.out.println("2.依赖注入");
        this.name = name;
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.BeanNameAware#setBeanName执行了!!!");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5.InitializingBean#afterPropertiesSet执行了!!!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9.DisposableBean#destroy执行了!!!");
    }
}
