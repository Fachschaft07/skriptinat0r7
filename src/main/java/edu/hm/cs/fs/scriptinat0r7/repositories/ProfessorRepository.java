package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Professor;

/**
 * Repository to execute CRUD operations on {@code Professor}s.
 */
public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Integer> {

    /**
     * Finds a {@code Professor} by its first or last-name. If first or last
     * name contains the search query then the professor is treated as a match.
     *
     * @param name
     *            the name of the {@code Professor} to search.
     * @return a {@code List} of all professors matching the given query.
     */
    @Query("select u from #{#entityName} u where u.firstName like %:name% or u.lastName like %:name%")
    List<Professor> findByFirstNameContainingOrLastNameContaining(@Param("name") String name);

    @Override
    List<Professor> findAll(Sort sort);

    /**
     * Finds all {@code Professor}s.
     *
     * @return a {@code List} of all professors.
     */
    @Override
    Collection<Professor> findAll();

    Professor findByLecturesIn(Lecture lecture);

}
