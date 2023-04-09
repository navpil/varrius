package io.github.navpil.varrius;

import io.github.navpil.varrius.guitars.GuitarResource;
import io.github.navpil.varrius.jersey.AutoScanFeature;
import io.github.navpil.varrius.jersey.JerseyApplicationConfig;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/jerseyauto/*")
public class JerseyAutoscanApplication extends ResourceConfig {

    public JerseyAutoscanApplication() {
        super(
                GuitarResource.class
        );
        /*Autoscan requires:
        *  - annotate all needed components with @Service and @Contract (package org.jvnet.hk2.annotations)
        *  - register AutoScanFeature.class
        *  - add the org.glassfish.hk2:hk2-metadata-generator dependency, so it will generate DI configuration (compile time)
        * */

        register(AutoScanFeature.class);
    }
}
