package com.hhy.spring.web;

import com.hhy.spring.framework.annotation.Autowired;
import com.hhy.spring.framework.annotation.Component;
import com.hhy.spring.framework.annotation.PostConstruct;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;


/**
 * <p>
 * 描述: TODO
 * </p>
 *
 * @Author huhongyuan
 */
@Component
public class TomcatServer {
    @Autowired
    private DispatcherServlet dispatcherServlet;

    @PostConstruct
    public void start() throws LifecycleException {
//        LogManager.getLogManager().reset();
//        SLF4JBridgeHandler.removeHandlersForRootLogger();
//        SLF4JBridgeHandler.install();
        // 设置端口
        int port = 8080;
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();

        // 设置上下文路径和文档基础目录
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        // 添加 Servlet
        tomcat.addServlet(contextPath, "dispatcherServlet", dispatcherServlet);
        context.addServletMappingDecoded("/*", "dispatcherServlet");

        // 启动 Tomcat
        tomcat.start();
        System.out.println("tomcat start...  port :" + port);
    }
}
