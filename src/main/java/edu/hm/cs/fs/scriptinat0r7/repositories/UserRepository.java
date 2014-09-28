package edu.hm.cs.fs.scriptinat0r7.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    public User findByFacultyID(String name);

}
