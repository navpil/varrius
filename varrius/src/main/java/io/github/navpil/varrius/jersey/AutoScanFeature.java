package io.github.navpil.varrius.jersey;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import org.glassfish.hk2.api.DynamicConfigurationService;
import org.glassfish.hk2.api.MultiException;
import org.glassfish.hk2.api.Populator;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ClasspathDescriptorFileFinder;
import org.glassfish.hk2.utilities.DuplicatePostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//https://mkyong.com/webservices/jax-rs/jersey-and-hk2-dependency-injection-auto-scanning/
public class AutoScanFeature implements Feature {

    private static final Logger LOG = LoggerFactory.getLogger(AutoScanFeature.class);
    @Inject
    ServiceLocator serviceLocator;

    @Override
    public boolean configure(FeatureContext context) {

        DynamicConfigurationService dcs =
                serviceLocator.getService(DynamicConfigurationService.class);
        Populator populator = dcs.getPopulator();
        try {
            // Populator - populate HK2 service locators from inhabitants files
            // ClasspathDescriptorFileFinder - find files from META-INF/hk2-locator/default
            populator.populate(
                    new ClasspathDescriptorFileFinder(this.getClass().getClassLoader()),
                    new DuplicatePostProcessor());

        } catch (IOException | MultiException ex) {
            LOG.error("Error happened", ex);
        }
        return true;
    }

}
