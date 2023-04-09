package io.github.navpil.varrius.oldschool;

import io.github.navpil.guitars.GuitarService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.ServiceLoader;

@WebListener
public class SPIGuitarServletInitialize implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //Built in Java SE SPI functionality
        ServiceLoader<GuitarService> guitarProviderLoader = ServiceLoader.load(GuitarService.class);
        GuitarService guitarService = null;
        int maxPriority = -1;
        for (GuitarService provider : guitarProviderLoader) {
            if (provider.getPriority() > maxPriority) {
                maxPriority = provider.getPriority();
                guitarService = provider;
            }
        }

        ServletContext context = servletContextEvent.getServletContext();
        context.addServlet("guitar-old-school", new GuitarServlet(guitarService))
                .addMapping("/oldschool/guitars");
    }
}
