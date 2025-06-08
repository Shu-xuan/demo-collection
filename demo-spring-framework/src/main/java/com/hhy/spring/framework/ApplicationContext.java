package com.hhy.spring.framework;

import com.hhy.spring.framework.annotation.Component;
import com.hhy.spring.framework.config.BeanPostProcessor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 描述: 应用程序上下文
 * </p>
 *
 * @Author huhongyuan
 */
public class ApplicationContext {
    /**
     * 存放已初始化完毕的 bean
     */
    private Map<String, Object> IOC = new HashMap<>();
    /**
     * 存放已实例化但尚未初始化完毕的 bean
     */
    private Map<String, Object> loadingIOC = new HashMap<>();
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public ApplicationContext(String pkgName) throws Exception {
        initContext(pkgName);
    }

    public void initContext(String pkgName) throws Exception {
        // 创建所有@Component标注的类的 BeanDefinition
        scanPackage(pkgName).stream()
                .filter(this::canCreate)
                .forEach(this::wrapper);
        // 创建所有初始化生命周期方法
        beanDefinitionMap.values().stream()
                .filter(beanDefinition -> BeanPostProcessor.class.isAssignableFrom(beanDefinition.getBeanType()))
                .map(this::createBean)
                .forEach(bean -> beanPostProcessors.add((BeanPostProcessor) bean));
        // 创建所有Bean
        beanDefinitionMap.values().stream()
                .forEach(this::createBean);
    }

    protected boolean canCreate(Class<?> type) {
        return type.isAnnotationPresent(Component.class);
    }

    protected BeanDefinition wrapper(Class<?> type) {
        BeanDefinition beanDefinition = new BeanDefinition(type);
        if (beanDefinitionMap.containsKey(beanDefinition.getName())) {
            throw new RuntimeException("重复的Bean名称: " + beanDefinition.getName());
        }
        beanDefinitionMap.put(beanDefinition.getName(), beanDefinition);
        return beanDefinition;
    }

    protected Object createBean(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        if (IOC.containsKey(name)) {
            return IOC.get(name);
        }
        // 创建Bean
        return doCreateBean(beanDefinition);
    }


    private Object doCreateBean(BeanDefinition beanDefinition) {
        //            System.out.println("正在创建Bean: " + beanDefinition.getName());
        Constructor constructor = beanDefinition.getConstructor();
        String beanName = beanDefinition.getName();
        Object bean = null;
        try {
            bean = constructor.newInstance();
            loadingIOC.put(beanName, bean);
            // Autowired依赖注入
            doFieldDI(bean, beanDefinition.getNeedAutowire());
            // 执行PostConstruct方法
            Object initializedBean = initializeBean(beanDefinition, bean);
            loadingIOC.remove(beanName);
            IOC.put(beanName, initializedBean);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bean;
    }

    private Object initializeBean(BeanDefinition beanDefinition, Object bean) {
        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessBeforeInitialization(bean, beanDefinition.getName());
        }
        Method postConstructor = beanDefinition.getPostConstructMethod();
        if (postConstructor != null) {
            try {
                postConstructor.invoke(bean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessAfterInitialization(bean, beanDefinition.getName());
        }
        return bean;
    }

    private void doFieldDI(Object bean, List<Field> needAutowire) {
        for (Field field : needAutowire) {
            field.setAccessible(true);
            try {
                field.set(bean, getBean(field.getType()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Class<?>> scanPackage(String pkgName) throws IOException, URISyntaxException {
//        System.out.println("扫描目录[ " + pkgName + " ]中");
        List<Class<?>> ans = new ArrayList<>();
        URL resource = ApplicationContext.class.getClassLoader().getResource(pkgName.replace(".", File.separator));
        Path path = Paths.get(resource.toURI());
        Files.walkFileTree(path, new SimpleFileVisitor() {
            @Override
            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                Path absolutePath = ((Path) file).toAbsolutePath();
                if (absolutePath.toString().endsWith(".class")) {
                    String replaceStr = absolutePath.toString().replace(File.separator, ".");
                    int pkgIndex = replaceStr.indexOf(pkgName);
                    String clazz = replaceStr.substring(pkgIndex, replaceStr.lastIndexOf("."));
//                    System.out.println(clazz);
                    try {
                        Class<?> toCreate = Class.forName(clazz);
                        ans.add(toCreate);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return ans;
    }

    /**
     * 通过名称获取Bean
     */
    public Object getBean(String beanName) {
        if (beanName == null) {
            return null;
        }
        Object bean = IOC.get(beanName);
        if (bean != null) {
            return bean;
        }
        Object loadingBean = loadingIOC.get(beanName);
        if (loadingBean != null) {
            return loadingBean;
        }
        if (beanDefinitionMap.containsKey(beanName)) {
            return createBean(beanDefinitionMap.get(beanName));
        }
        return null;
    }

    /**
     * 通过类型获取Bean
     */
    public <T> T getBean(Class<T> type) {
        String beanName = this.beanDefinitionMap.values().stream()
                .filter(bd -> type.isAssignableFrom(bd.getBeanType()))
                .map(BeanDefinition::getName)
                .findFirst()
                .orElse(null);
        return (T) getBean(beanName);
    }

    /**
     * 通过类型获取Beans
     */
    public <T> List<T> getBeans(Class<T> type) {
        return this.beanDefinitionMap.values().stream()
                .filter(bd -> type.isAssignableFrom(bd.getBeanType()))
                .map(BeanDefinition::getName)
                .map(this::getBean)
                .map(bean -> (T) bean)
                .collect(Collectors.toList());
    }
}
