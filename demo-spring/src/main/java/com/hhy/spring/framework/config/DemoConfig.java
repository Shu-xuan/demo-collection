package com.hhy.spring.framework.config;

import com.hhy.spring.entity.JW1;
import org.springframework.context.annotation.Bean;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/11 10:43
 */
public class DemoConfig {
    @Bean
    public JW1 jw() {
        return new JW1();
    }
}
