package com.hhy.spring;

import com.hhy.spring.lifecycle.bean.Person;
import com.hhy.spring.service.IMyservice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 23:25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LifeCycleTest {
    private ApplicationContext context;
    @Before
    public void doInit() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void lf1st() {
        Person person = (Person) context.getBean("person");
        // 使用bean
        person.getName();
        // 关闭容器才会触发销毁
        ((ClassPathXmlApplicationContext)context).close();
    }
}
