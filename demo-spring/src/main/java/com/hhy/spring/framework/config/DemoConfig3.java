package com.hhy.spring.framework.config;

import com.hhy.spring.registrar.JWRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/11 11:16
 */
@Configuration
@Import(JWRegistrar.class)
public class DemoConfig3 {
}
