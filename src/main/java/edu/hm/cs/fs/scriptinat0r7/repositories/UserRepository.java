package edu.hm.cs.fs.scriptinat0r7.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.User;

/**
 * Repository to execute CRUD operations on {@code User}s.
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    /**
     * Searches a user via a given ifw account id.
     * @param facultyId the ifw account id to search for.
     * @return the corresponding user object.
     */
    User findByFacultyID(String facultyId);

}
