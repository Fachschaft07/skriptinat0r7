package edu.hm.cs.fs.scriptinat0r7;

import edu.hm.cs.fs.scriptinat0r7.authentication.IfwUserDetailsService;
import edu.hm.cs.fs.scriptinat0r7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IfwUserDetailsService ifwUserDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                    .antMatchers("/resources/**").permitAll()
                    .antMatchers("/login.do").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login.do")
                    .failureUrl("/login?error")
                    .defaultSuccessUrl("/")
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .logout().permitAll();
        // @formatter:on
    }

    @Bean
    public IfwUserDetailsService ifwUserDetailsService(final JdbcTemplate ifwJdbc, final UserService userService) {
        return new IfwUserDetailsService(ifwJdbc, userService);
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        final RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_FACHSCHAFTLER > ROLE_USER");
        return roleHierarchy;
    }

    @Bean
    public AuthenticatedVoter authenticatedVoter() {
        return new AuthenticatedVoter();
    }

    @Bean
    public AffirmativeBased accessDecisionManager(
            final RoleHierarchy roleHierarchy,
            final AuthenticatedVoter authenticatedVoter) {
        final RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy);
        return new AffirmativeBased(Arrays.asList(roleHierarchyVoter, authenticatedVoter));
    }

    @Autowired
    public void configureGlobal(
            final AuthenticationManagerBuilder auth,
            final IfwUserDetailsService ifwUserDetailsService) throws Exception {
        auth.userDetailsService(ifwUserDetailsService);
    }

    @Bean
    public DefaultWebSecurityExpressionHandler webexpressionHandler(final RoleHierarchy roleHierarchy) {
        final DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return ifwUserDetailsService;
    }
}
