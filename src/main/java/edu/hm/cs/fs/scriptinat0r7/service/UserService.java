package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.User;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.repositories.UserRepository;

/**
 * A service for business operations on users.
 */
@Service
public class UserService {

    private final Pattern ifwUserPattern = Pattern.compile("^if(w|s)[0-9]{5}$");

    @Autowired
    private UserRepository users;

    /**
     * Returns the questioned user or creates it if it does not yet exist.
     * @param facultyId the faculty id of the user.
     * @return a new user instance or an persisted one.
     */
    public User findOrCreateByFacultyID(final String facultyId) {
        if (!isStudentAccount(facultyId)) {
            throw new IllegalArgumentException("not a valid student account");
        }

        User user = users.findByFacultyID(facultyId);

        if (user == null) {
            final User newUser = new User();
            newUser.setFacultyID(facultyId);
            newUser.setRole(Role.USER);
            user = users.save(newUser);
        }

        return user;
    }

    /**
     * Returns true if the given ifw account name is a valid student user account name.
     * @param ifwAccount the ifw account name to check.
     * @return true if valid, else false.
     */
    public boolean isStudentAccount(final String ifwAccount) {
        return ifwUserPattern.matcher(ifwAccount).matches();
    }

}
