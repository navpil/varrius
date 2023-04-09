package io.github.navpil.varrius.jersey;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.annotation.WebListener;
import org.glassfish.jersey.servlet.ServletContainer;

@WebListener
public class JerseyProgrammaticStartup implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext context = sce.getServletContext();

        final ServletContainer s = new ServletContainer(new JerseyApplicationConfig());
        final ServletRegistration.Dynamic dsr = context.addServlet("custom.JerseyApplicationConfig", s);

        dsr.setAsyncSupported(true);
        dsr.setLoadOnStartup(1);
        dsr.addMapping("/mycustommapping/*");
    }

}
