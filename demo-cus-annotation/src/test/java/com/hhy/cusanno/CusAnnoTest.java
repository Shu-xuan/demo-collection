package com.hhy.cusanno;

import com.hhy.cusanno.service.WxzService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest(classes = CusAnnoApplication.class)
public class CusAnnoTest {
    @Autowired
    @Qualifier("wxz")
    private WxzService wxzService;
    @Test
    public void testAnno() {
        wxzService.Juan();
    }
}
