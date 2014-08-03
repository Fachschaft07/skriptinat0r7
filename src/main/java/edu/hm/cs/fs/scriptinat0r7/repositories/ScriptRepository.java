package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Script;

/**
 * Repository to execute CRUD operations on {@code Script}s.
 *
 */
public interface ScriptRepository extends PagingAndSortingRepository<Script, Long> {

    /**
     * Finds a {@code Script} by its name.
     * 
     * @param name
     *            the name of the {@code Script} to search.
     * @return a {@code List} of all scripts matching the given name.
     */
    List<Script> findByNameContaining(String name);

}
