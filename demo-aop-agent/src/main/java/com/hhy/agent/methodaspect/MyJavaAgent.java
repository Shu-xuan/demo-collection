package com.hhy.agent.methodaspect;

import java.lang.instrument.Instrumentation;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;枢璇</p>
 *
 * @author 枢璇
 * @Version 1.0.0
 * @since 2025/3/12 23:03
 */
public class MyJavaAgent {
    /**
     * premain方法是JavaAgent的入口方法，会在JVM启动时，加载JavaAgent之前被调用，
     * 主要作用是在Java Agent加载之前做一些初始化操作，例如设置一些系统属性，读取配置文件等
     * @param args
     * @param instrumentation
     */
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("MyJavaAgent is running!");
        MyTransformer transformer = new MyTransformer();
        instrumentation.addTransformer(transformer);
    }
}
