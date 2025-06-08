package com.hhy.proxy.cglib;

import com.hhy.proxy.service.impl.WxzIJuanImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/10 20:36
 */
public class CGlibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WxzIJuanImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("在: " + method.getName() + "前执行");
                Object result = methodProxy.invokeSuper(obj, args);
                System.out.println("在: " + method.getName() + "后执行");
                return result;
            }
        });
        WxzIJuanImpl wxzIJuan = (WxzIJuanImpl) enhancer.create();
        wxzIJuan.IJuan();
    }
}
