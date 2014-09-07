package edu.hm.cs.fs.scriptinat0r7.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import edu.hm.cs.fs.scriptinat0r7.config.SecurityConfiguration;
import edu.hm.cs.fs.scriptinat0r7.config.WebConfiguration;

/**
 * Class responsible for initializing configuration for Spring.
 */
public class WebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    /**
     * Constructor which passes additional classes with configuration.
     */
    public WebApplicationInitializer() {
        super(SecurityConfiguration.class, WebConfiguration.class);
    }

}
