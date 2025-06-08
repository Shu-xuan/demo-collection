package com.hhy.spring;

import com.hhy.spring.framework.config.DemoConfig;
import com.hhy.spring.framework.config.DemoConfig2;
import com.hhy.spring.framework.config.DemoConfig3;
import com.hhy.spring.entity.JW1;
import com.hhy.spring.entity.JW2;
import com.hhy.spring.entity.JW3;
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
 * @since 2025/3/11 10:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestImport {
    private ApplicationContext context;
    @Before
    public void doInit() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testImport1() {
        System.out.println(context.getBean(DemoConfig.class));
        System.out.println(context.getBean(JW1.class));
    }
    @Test
    public void testImport2() {
        System.out.println(context.getBean(DemoConfig2.class));
        System.out.println(context.getBean(JW2.class));
    }
    @Test
    public void testImport3() {
        System.out.println(context.getBean(DemoConfig3.class));
        System.out.println(context.getBean("wxz",JW3.class).getClass().getSimpleName());
    }
}
