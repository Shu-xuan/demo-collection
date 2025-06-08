package com.hhy.spring;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hhy.spring.entity.User;
import com.hhy.spring.mapper.IUserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/10 21:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTXAOP {
    private ApplicationContext context;

    private IUserMapper userMapper;
    @Before
    public void doInit() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userMapper = context.getBean(IUserMapper.class);
    }

    @Test
    @Transactional
    public void testTX01() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, 1012);
        User user = new User();
        user.setNickName("wxz");
        userMapper.update(user, updateWrapper);
//        System.out.println(1 / 0);
    }
}
