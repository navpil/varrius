package io.github.navpil.varrius.oldschool;

import io.github.navpil.varrius.guitars.UkrainianGuitarService;
import io.github.navpil.guitars.GuitarService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ManualDIGuitarServletInitialize implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //Manual DI, guitar service is singleton
        GuitarService guitarService = new UkrainianGuitarService();

        // ... create other components here ...

        ServletContext context = servletContextEvent.getServletContext();
        context.addServlet("guitar-ua", new GuitarServlet(guitarService))
                .addMapping("/oldschool/uaguitars");

        // ... register other servlets here ...
    }

}
