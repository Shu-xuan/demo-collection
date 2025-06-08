package com.hhy.spring;

import com.hhy.spring.service.IMyservice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
 * @since 2025/3/9 21:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestAOP {

    private IMyservice myservice;
    @Before
    public void doInit() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        myservice = context.getBean(IMyservice.class);
    }

    @Test
    public void testBefore() {
        myservice.doSth();
    }

    @Test
    public void testAfter() {
        System.out.println(myservice.rtSth());
    }
}
