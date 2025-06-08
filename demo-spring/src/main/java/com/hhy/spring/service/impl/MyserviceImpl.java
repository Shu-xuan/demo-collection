package com.hhy.spring.service.impl;

import com.hhy.spring.service.IMyservice;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/9 21:09
 */
@Service
public class MyserviceImpl implements IMyservice {
    @Override
    public void doSth() {
        System.out.println("==== doSomething ing ====");
    }

    @Override
    public String rtSth() {
        return "六百六十六";
    }
}
