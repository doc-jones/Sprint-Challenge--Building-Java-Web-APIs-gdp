package com.lambdaschool.gdp.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class GdpApplication {
    public static GDPList gdpList = new GDPList();
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(GdpApplication.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}


// Comment to commit