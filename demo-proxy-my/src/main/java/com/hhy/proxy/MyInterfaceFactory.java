package com.hhy.proxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 描述: 接口工厂
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/25 16:41
 */
public class MyInterfaceFactory {
    private static AtomicInteger count = new AtomicInteger();

    /**
     * 获得代理类名
     */
    private static String getClassName() {
        return "MyInterface$proxy" + count.incrementAndGet();
    }

    /**
     * 创建代理类实例
     */
    private static MyInterface newInstance(String className, MyHandler handler) throws Exception {
        Class<?> aClass = MyInterfaceFactory.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        MyInterface proxy = (MyInterface) constructor.newInstance();
        // 依赖注入
        handler.setProxy(proxy);
        return proxy;
    }

    public static MyInterface createProxyObject(MyHandler handler) throws Exception {
        String className = getClassName();
        File javaFile = createJavaFile(className, handler);
        Compiler.compile(javaFile);
        return newInstance("com.hhy.proxy." + className, handler);
    }

    /**
     * 创建Java文件
     */
    private static File createJavaFile(String className, MyHandler handler) throws IOException {
        String func1Body = handler.getFuncBody("func1");
        String func2Body = handler.getFuncBody("func2");
        String func3Body = handler.getFuncBody("func3");
        String context = "package com.hhy.proxy;\n" +
                "\n" +
                "public class " + className + " implements MyInterface{\n" +
                "    MyInterface myInterface;\n" +
                "    @Override\n" +
                "    public void func1() {\n" +
                func1Body +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func2() {\n" +
                func2Body +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void func3() {\n" +
                func3Body +
                "    }\n" +
                "}\n";
        File file = new File(className + ".java");
        Files.write(file.toPath(), context.getBytes());
        return file;
    }


}
