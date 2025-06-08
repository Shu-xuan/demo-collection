package com.hhy.spring.web;

import com.alibaba.fastjson2.JSON;
import com.hhy.spring.framework.annotation.Component;
import com.hhy.spring.framework.config.BeanPostProcessor;
import com.hhy.spring.web.annotation.Controller;
import com.hhy.spring.web.annotation.Param;
import com.hhy.spring.web.annotation.RequestMapping;
import com.hhy.spring.web.annotation.ResponseBody;
import com.hhy.spring.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
@Component
public class DispatcherServlet extends HttpServlet implements BeanPostProcessor {
    public Map<String, WebHandler> handlerMap = new HashMap<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebHandler handler = findHandler(req);
        if (handler == null) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.getWriter().write("<h1>无对应Mapping!</h1> <br> " + req.getRequestURL().toString());
            return;
        }
        try {
            Object controllerBean = handler.getControllerBean();
            // 方法参数获取
            Object[] args = resolveArgs(req, handler.getMethod());
            Object result = handler.getMethod().invoke(controllerBean, args);
            switch (handler.getResultType()) {
                case HTML: {
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.getWriter().write(result.toString());
                    break;
                }
                case JSON: {
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write(JSON.toJSONString(result));
                    break;
                }
                case LOCAL: {
                    ModelAndView mv = (ModelAndView) result;
                    Map<String, String> parameters = mv.getParameters();
                    String view = mv.getView();
                    InputStream resource = this.getClass().getClassLoader().getResourceAsStream(view);
                    if (resource != null) {
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        byte[] buf = new byte[1024];
                        int nRead;
                        while ((nRead = resource.read(buf, 0, buf.length)) != -1) {
                            buffer.write(buf, 0, nRead);
                        }
                        String html = new String(buffer.toByteArray(), java.nio.charset.StandardCharsets.UTF_8);
                        for (Map.Entry<String, String> entry : parameters.entrySet()) {
                            // 模板引擎 $hhy{param}
                            if (html.contains("$hhy{" + entry.getKey() + "}")) {
                                String placeholder = "\\$hhy\\{" + entry.getKey() + "\\}";
                                html = html.replaceAll(placeholder, entry.getValue());
                            }
                        }
                        resp.setContentType("text/html;charset=UTF-8");
                        resp.getWriter().write(html);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] resolveArgs(HttpServletRequest req, Method method) {
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            String value = parameter.getAnnotation(Param.class) != null ? req.getParameter(parameter.getAnnotation(Param.class).value()) : parameter.getName();
            Class<?> type = parameter.getType();
            if (String.class.isAssignableFrom(type)) {
                args[i] = value;
            } else if (Integer.class.isAssignableFrom(type)) {
                args[i] = Integer.parseInt(value);
            } else {
                args[i] = 0;
            }
        }

        return args;
    }

    private WebHandler findHandler(HttpServletRequest req) {
        return handlerMap.get(req.getRequestURI());
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (!bean.getClass().isAnnotationPresent(Controller.class)) {
            return bean;
        }
        RequestMapping classRm = bean.getClass().getAnnotation(RequestMapping.class);
        String classUrl = classRm != null ? classRm.value() : "";
        Arrays.stream(bean.getClass().getMethods())
                .filter(method -> method.isAnnotationPresent(RequestMapping.class))
                .forEach(method -> {
                    RequestMapping methodRm = method.getAnnotation(RequestMapping.class);
                    String key = classUrl.concat(methodRm.value());
                    WebHandler webHandler = new WebHandler(bean, method);
                    if (handlerMap.putIfAbsent(key, webHandler) != null) {
                        System.err.println("controller重复定义: " + key);
                        System.exit(1); // 立即终止JVM
                    }
                });
        return bean;
    }
}
