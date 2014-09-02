package edu.hm.cs.fs.scriptinat0r7.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Class responsible for configuring spring security.
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * Configures authentication store.
     * @param auth The AuthenticationManagerBuilder from Spring.
     * @throws Exception Contract from AuthenticationManagerBuilder.
     */
    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("password")
                .roles("USER");
    }

    /**
     * Method used for route matching / which route needs authentication.
     * @param http The HttpSecurity from Spring.
     * @throws Exception Contract from HttpSecurity.
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll();
    }
}
