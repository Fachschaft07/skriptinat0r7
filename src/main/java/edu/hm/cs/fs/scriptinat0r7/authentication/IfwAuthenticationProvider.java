package edu.hm.cs.fs.scriptinat0r7.authentication;

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

/**
 * This class is responsible for authenticating users via an ifw jdbc connection.
 */
public class IfwAuthenticationProvider implements UserDetailsService {

    private final JdbcTemplate ifwJdbc;
    private final UsersService userService;

    /**
     * The constructor.
     * @param ifwJdbc A jdbc connection to the ifw source.
     * @param userService The user service to register users in our application.
     */
    public IfwAuthenticationProvider(final JdbcTemplate ifwJdbc, final UsersService userService) {
        this.ifwJdbc = ifwJdbc;
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {
        try {
            RowMapper<UserDetails> mapper = new IfwAccountMapper();
            return ifwJdbc.queryForObject("SELECT name, password FROM ifw WHERE name = ?", mapper, username);
        } catch (DataAccessException e) {
            throw new UsernameNotFoundException(username, e);
        }
    }

    /**
     * The ifw account mapper.
     */
    private class IfwAccountMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            final String ifwAccount = rs.getString("name");
            if (userService.isStudentAccount(ifwAccount)) {
                final User user = userService.findOrCreateByFacultyID(ifwAccount);
                final String ifwPassword = rs.getString("password");
                user.setPassword(ifwPassword);
                return user;
            } else {
                throw new IllegalArgumentException("non student users are not yet supported"); // TODO: implement professor accounts
            }
        }
    }

}
