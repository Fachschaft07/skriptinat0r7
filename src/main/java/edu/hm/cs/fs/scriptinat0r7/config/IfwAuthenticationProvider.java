package edu.hm.cs.fs.scriptinat0r7.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.service.UsersService;

public class IfwAuthenticationProvider implements UserDetailsService {

    private JdbcTemplate ifwJdbc;
    private UsersService userService;

    public IfwAuthenticationProvider(JdbcTemplate ifwJdbc, UsersService userService) {
        this.ifwJdbc = ifwJdbc;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        try {
            RowMapper<UserDetails> mapper = new IfwAccountMapper();
            return ifwJdbc.queryForObject("SELECT name, password FROM ifw WHERE name = ?", mapper, username);
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(username, e);
        }
    }

    private class IfwAccountMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            final String ifwAccount = rs.getString("name");
            final User user = userService.findByFacultyID(ifwAccount);
            final String ifwPassword = rs.getString("password");
            user.setPassword(ifwPassword);
            return user;
        }
    }

}
