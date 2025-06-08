package com.hhy.proxy;

/**
 * <p>
 * 描述:
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/25 17:56
 */
public interface MyHandler {
    String getFuncBody(String funcName);

    default void setProxy(MyInterface proxy) {

    }
}
