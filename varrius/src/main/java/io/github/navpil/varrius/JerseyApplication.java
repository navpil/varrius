package io.github.navpil.varrius;

import io.github.navpil.varrius.jersey.JerseyApplicationConfig;
import jakarta.ws.rs.ApplicationPath;

@ApplicationPath("/jersey/*")
public class JerseyApplication extends JerseyApplicationConfig {
}
