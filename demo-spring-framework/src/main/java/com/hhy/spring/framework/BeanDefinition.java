package com.hhy.spring.framework;

import com.hhy.spring.framework.annotation.Autowired;
import com.hhy.spring.framework.annotation.Component;
import com.hhy.spring.framework.annotation.PostConstruct;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
public class BeanDefinition {
    private final String name;
    private final Constructor constructor;
    private final Class<?> beanType;
    private final Method postConstructMethod;

    /**
     * 记录需要注入的属性
     */
    private List<Field> needAutowire;

    public BeanDefinition(Class<?> type) {
        this.beanType = type;
        Component component = type.getAnnotation(Component.class);
        String simpleName = type.getSimpleName();

        if (!component.name().isEmpty()) {
            this.name = component.name();
        } else if (simpleName.length() == 0) {
            this.name = "";
        } else if (simpleName.length() > 1 && Character.isUpperCase(simpleName.charAt(1))) {
            this.name = simpleName;
        } else {
            this.name = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
        }

        try {
            this.constructor = type.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Method initMethod = Arrays.stream(type.getMethods())
                .filter(method -> method.isAnnotationPresent(PostConstruct.class))
                .findFirst().orElse(null);
        postConstructMethod = initMethod;


        needAutowire = Arrays.stream(type.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Autowired.class))
                .collect(Collectors.toList());
    }


    public String getName() {
        return this.name;
    }

    public Constructor getConstructor() {
        return constructor;
    }

    public Method getPostConstructMethod() {
        return postConstructMethod;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public List<Field> getNeedAutowire() {
        return needAutowire;
    }
}
