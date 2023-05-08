package io.github.navpil.varrius.springannotated;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("io.github.navpil.varrius.spring")
@EnableWebMvc
public class AppConfig {
}

