package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.repositories.UserRepository;

@Service
public class UsersService {

    private final Pattern ifwUserPattern = Pattern.compile("^if(w|s)[0-9]{5}$");

    @Autowired
    private UserRepository users;

    public User findOrCreateByFacultyID(String facultyId) {
        if (!isStudentAccount(facultyId)) {
            throw new IllegalArgumentException("not a valid student account");
        }

        User user = users.findByFacultyID(facultyId);

        if(user == null) {
            final User newUser = new User();
            newUser.setFacultyID(facultyId);
            newUser.setRole(Role.USER);
            user = users.save(newUser);
        }

        return user;
    }

    public boolean isStudentAccount(String ifwAccount) {
        return ifwUserPattern.matcher(ifwAccount).matches();
    }

}
