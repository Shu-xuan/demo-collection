package com.hhy.spring.web;

import com.hhy.spring.web.annotation.ResponseBody;
import com.hhy.spring.web.servlet.ModelAndView;

import java.lang.reflect.Method;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class WebHandler {
    private final Object controllerBean;
    private final Method method;
    private final ResultType resultType;
    public WebHandler(Object controllerBean, Method method) {
        this.controllerBean = controllerBean;
        this.method = method;
        this.resultType = resolveResultType(controllerBean, method);
    }

    private ResultType resolveResultType(Object controllerBean, Method method) {
        if (method.isAnnotationPresent(ResponseBody.class) || controllerBean.getClass().isAnnotationPresent(ResponseBody.class)) {
            return ResultType.JSON;
        }
        if (method.getReturnType() == ModelAndView.class) {
            return ResultType.LOCAL;
        }
        return ResultType.HTML;
    }

    public Object getControllerBean() {
        return controllerBean;
    }

    public Method getMethod() {
        return method;
    }

    public ResultType getResultType() {
        return resultType;
    }

    enum ResultType {
        JSON, HTML, LOCAL
    }
}
