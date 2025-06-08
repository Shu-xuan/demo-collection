package com.hhy.proxy;

import java.lang.reflect.Field;

/**
 * <p>
 * 描述: TODO
 * </p>
 * <p>版权所有: &copy;古月HYuan</p>
 *
 * @Author 枢璇
 * @Date 2025/3/25 17:49
 */
public class Main {
    public static void main(String[] args) throws Exception {
        MyInterface myInterface = MyInterfaceFactory.createProxyObject(new PrintFuncName());
        myInterface.func1();
        System.out.println("=======");

        myInterface = MyInterfaceFactory.createProxyObject(new PrintFuncName1());
        myInterface.func1();
        System.out.println("=======");

        myInterface = MyInterfaceFactory.createProxyObject(new LogHandler(myInterface));
        myInterface.func1();
    }

    static class PrintFuncName implements MyHandler {
        @Override
        public String getFuncBody(String funcName) {
            return "        System.out.println(\""+ funcName +"\");\n";
        }
    }
    static class PrintFuncName1 implements MyHandler {
        @Override
        public String getFuncBody(String funcName) {
            StringBuilder builder = new StringBuilder();
            builder.append("System.out.println(1);")
                    .append("System.out.println(\""+ funcName +"\");\n");
            return builder.toString();
        }
    }

    static class LogHandler implements MyHandler {
        private MyInterface myInterface;

        public LogHandler(MyInterface myInterface) {
            this.myInterface = myInterface;
        }

        @Override

        public String getFuncBody(String funcName) {
            return "        System.out.println(\"before\");\n" +
                    "        myInterface." + funcName + "();\n" +
                    "        System.out.println(\"after\");";
        }

        @Override
        public void setProxy(MyInterface proxy)  {
            Field myInterfaceField = null;
            try {
                myInterfaceField = proxy.getClass().getDeclaredField("myInterface");
                myInterfaceField.setAccessible(true);
                myInterfaceField.set(proxy, this.myInterface);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
