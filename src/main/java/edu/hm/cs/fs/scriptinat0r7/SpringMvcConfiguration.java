package edu.hm.cs.fs.scriptinat0r7;

import edu.hm.cs.fs.scriptinat0r7.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvcConfiguration {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(RequestInterceptor requestInterceptor) {
        return new WebMvcConfigurer(requestInterceptor);
    }

    public static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

        private final RequestInterceptor requestInterceptor;

        public WebMvcConfigurer(RequestInterceptor requestInterceptor) {
            this.requestInterceptor = requestInterceptor;
        }

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(requestInterceptor);
        }
    }

}
