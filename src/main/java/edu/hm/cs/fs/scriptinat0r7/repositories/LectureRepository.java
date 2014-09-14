package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Script;

/**
 * Repository to execute CRUD operations on {@code Lecture}s.
 */
public interface LectureRepository extends PagingAndSortingRepository<Lecture, Integer> {

    /**
     * Finds all {@code Lecture}s.
     * @param sort Sort options
     * @return a {@code List} of all lectures.
     */
    @Override
    List<Lecture> findAll(Sort sort);

    /**
     * Find all lectures where the given query is in the name.
     * @param searchQuery the query to search for.
     * @return a list of matched lectures.
     */
    List<Lecture> findByNameContaining(String searchQuery);

    List<Lecture> findByUsedScriptsIn(Collection<Script> script);

}
