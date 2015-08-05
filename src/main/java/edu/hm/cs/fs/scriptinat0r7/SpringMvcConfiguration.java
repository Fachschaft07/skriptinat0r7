package edu.hm.cs.fs.scriptinat0r7;

import edu.hm.cs.fs.scriptinat0r7.interceptor.RequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

@Configuration
public class SpringMvcConfiguration {

    @Bean
    public FilterRegistrationBean registerSecurityFilter(@Qualifier(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME) final Filter securityFilter) {
        FilterRegistrationBean securityFilterChainRegistration = new FilterRegistrationBean(securityFilter);
        securityFilterChainRegistration.setName(AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME);
        securityFilterChainRegistration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return securityFilterChainRegistration;
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(final RequestInterceptor requestInterceptor) {
        return new WebMvcConfigurer(requestInterceptor);
    }

    public static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

        private final RequestInterceptor requestInterceptor;

        public WebMvcConfigurer(final RequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
        }

        @Override
        public void addInterceptors(final InterceptorRegistry registry) {
            registry.addInterceptor(requestInterceptor);
        }
    }

}
