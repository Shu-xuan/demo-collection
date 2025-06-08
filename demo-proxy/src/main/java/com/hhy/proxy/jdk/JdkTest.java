package com.hhy.proxy.jdk;

import com.hhy.proxy.service.IJuan;
import com.hhy.proxy.service.impl.WXZ;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/10 19:59
 */
public class JdkTest {
    public static void main(String[] args) {
        IJuan wxz = new WXZ();
        IJuan o = (IJuan) Proxy.newProxyInstance(
                wxz.getClass().getClassLoader(),
                wxz.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("代理输出: 卷王又在卷");
                    method.invoke(wxz, args1);
                    return null;
                });
        o.doJuan();
    }
}
