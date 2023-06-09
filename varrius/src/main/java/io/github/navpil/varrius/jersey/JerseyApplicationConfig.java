package io.github.navpil.varrius.jersey;

import io.github.navpil.guitars.GuitarService;
import io.github.navpil.varrius.guitars.GermanGuitarService;
import io.github.navpil.varrius.guitars.GuitarResource;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class JerseyApplicationConfig extends ResourceConfig {

    public JerseyApplicationConfig() {
        super(
                GuitarResource.class
        );
        register(new AbstractBinder(){
            @Override
            protected void configure() {
                // map this service to this contract
                bind(GermanGuitarService.class).to(GuitarService.class);
            }
        });
    }
}
