package edu.hm.cs.fs.scriptinat0r7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.repositories.UserRepository;

@Service
public class UsersService {

    @Autowired
    private UserRepository users;

    public User findByName(String name) {
        return users.findByName(name);
    }

}
