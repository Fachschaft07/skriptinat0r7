package edu.hm.cs.fs.scriptinat0r7.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import edu.hm.cs.fs.scriptinat0r7.service.UsersService;

public class IfwAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private JdbcTemplate ifwJdbc;
    private UsersService userService;
    
    public IfwAuthenticationProvider(JdbcTemplate ifwJdbc, UsersService userService) {
        this.ifwJdbc = ifwJdbc;
        this.userService = userService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // TODO Auto-generated method stub

    }

    @Override
    protected UserDetails retrieveUser(String username,
            UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        return userService.findByName(username);
    }
}
