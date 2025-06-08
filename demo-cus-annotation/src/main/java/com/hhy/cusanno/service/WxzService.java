package com.hhy.cusanno.service;

import com.hhy.cusanno.annotation.MethodExporter;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/12 21:27
 */
@Service("wxz")
public class WxzService {
    @MethodExporter
    public void Juan() {
        System.out.println("孩子们，我是wxz，我要开始卷了...");
    }
}
