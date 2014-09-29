package edu.hm.cs.fs.scriptinat0r7.model.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * The user roles.
 */
public enum Role {
    /** An ordinary, regular student. */
    USER,
    /** An fs-student. */
    FACHSCHAFTLER,
    /** A professor. */
    PROFESSOR,
    /** A locked user. */
    LOCKED,
    /** An author of a script. */
    AUTHOR;

    public GrantedAuthority asAuthority() {
        final String roleName = "ROLE_" + name();
        return new SimpleGrantedAuthority(roleName);
    }
}
