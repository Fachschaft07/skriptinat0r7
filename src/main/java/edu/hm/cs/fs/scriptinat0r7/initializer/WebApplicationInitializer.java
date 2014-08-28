package edu.hm.cs.fs.scriptinat0r7.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import edu.hm.cs.fs.scriptinat0r7.config.SecurityConfiguration;

public class WebApplicationInitializer  extends AbstractSecurityWebApplicationInitializer {
    public WebApplicationInitializer() {
        super(SecurityConfiguration.class);
    }
}
