# Various servlet technologies

## Simple JSP

Configured by `web.xml`, the `sample-jsp` servlet.

GET http://localhost:8080/varrius/sample-jsp

TODO: some better example (for example having a servlet behind)

## Old School servlets

`OldSchoolServlet`, configured through `web.xml`

GET http://localhost:8080/varrius/oldschool/webxml

`AnnotationServlet` configured with `@WebServlet`

GET http://localhost:8080/varrius/oldschool/annotation

Dependency Injection and Service Discovery can be done manually, like in `SPIGuitarServletInitialize`,
which registers `GuitarServlet` manually and uses the built-in SPI functionality:

GET http://localhost:8080/varrius/oldschool/guitars

But injection can be done manually, without any service discovery, as in `ManualDIGuitarServletInitialize`:

GET http://localhost:8080/varrius/oldschool/uaguitars

## Jersey

### Annotation based config

Check the `JerseyApplication`

How does it work? Why @ApplicationPath is picked up?

`jersey-container-servlet.jar` contains `JerseyServletContainerInitializer` which
implements `ServletContainerInitializer`, annotated with

    @HandlesTypes({ ... ApplicationPath.class})

and is picked up by Tomcat using SPI.

GET http://localhost:8080/varrius/jersey/guitars/list

### web.xml based Jersey config

Jersey can also be configured through servlet like this (though no example in this application):

    <servlet>
        <servlet-name>Jersey REST Service</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>io.github.navpil.bigb.jersey</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Jersey REST Service</servlet-name>
        <url-pattern>/jersey/*</url-pattern>
    </servlet-mapping>

### Programmatic configuration

Check the `JerseyProgrammaticStartup`.
With some meddling you may create a class which would be manually injected into some other servlet in this listener.
And that same class may be passed to the Jersey Config which will inject there a necessary dependency. 

For example:

    // in JerseyProgrammaticStartup:
    Eavesdrop eavesdrop = new Eavesdrop()
    final ServletContainer s = new ServletContainer(new JerseyApplicationConfig(eavesdrop));
    ...
    context.addServlet("EavesdropServlet", new EavesdropServlet(eavesdrop));

    // in JerseyApplicationConfig:
    register(new ContainerLifecycleListener() {
       public void onStartup(Container container) {
          ServiceLocator serviceLocator = container.getApplicationHandler().getInjectionManager()
               .getInstance(ServiceLocator.class);
          eavesdrop.setGuitarService(serviceLocator.getService(GuitarService.class));
       }
       ...
    }

GET http://localhost:8080/varrius/mycustommapping/guitars/list

### Autowiring HK2

Check the `JerseyAutoscanApplication`

AutoScan requires:

 - annotate all needed components with @Service and @Contract (package org.jvnet.hk2.annotations)
 - register io.github.navpil.varrius.jersey.AutoScanFeature.class
 - add the org.glassfish.hk2:hk2-metadata-generator dependency, so it will generate DI configuration (compile time)

       varrius\target\classes\META-INF\hk2-locator\default

GET http://localhost:8080/varrius/jerseyauto/guitars/list

Code taken from [mkyong](https://mkyong.com/webservices/jax-rs/jersey-and-hk2-dependency-injection-auto-scanning/)

### Jersey miscellaneous

[Various ways to deploy Jersey](https://stackoverflow.com/questions/45625925/what-exactly-is-the-resourceconfig-class-in-jersey-2)

_I could not find a way to register HttpServlets so that they can be managed by HK2 automatically, only manual config_ 

## Spring

Example shows the DispatcherServlet

GET http://localhost:8080/varrius/spring/cars/all

But it can also be configured by [annotations](https://efraimgentil.github.io/en/post/java/spring/configuring-spring-with-annotations)

TODO: annotation configuration

TODO: register servlet

## Everything servlet

This will redirect to Spring: 

GET http://localhost:8080/varrius/myapp/cars

This will redirect to Jersey:

GET http://localhost:8080/varrius/myapp/guitars

Logic of handling the requests can be much more complicated.
This way you can gradually migrate the application from one technology (servlets) to another (jersey or spring).

## Spring Boot and Quarkus

TODO: add Spring Boot and Quarkus examples.
(It seems Quarkus does not support `.war` files deployed on Tomcat)

## Note on Classloading

Classloading has the following order in Tomcat: 

 - Bootstrap classes of your JVM
 - System class loader classes
 - /WEB-INF/classes of your web application
 - /WEB-INF/lib/*.jar of your web application
 - Common class loader classes

I was tempted to put some library into the /lib folder into tomcat so that it's loaded with the same classloader
and thus its static fields can be shared across application, but **don't do it**.
At the very least:

 - Leaking abstraction/tight coupling
 - Accidentally including that same library into .war will result in a separate instance of a class for this app
 - Putting some listeners into a static class in the /lib folder results in memory leaks

