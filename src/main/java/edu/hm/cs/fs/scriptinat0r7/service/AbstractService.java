package edu.hm.cs.fs.scriptinat0r7.service;

import org.springframework.security.core.context.SecurityContextHolder;

import edu.hm.cs.fs.scriptinat0r7.model.User;

public class AbstractService {

    protected User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
