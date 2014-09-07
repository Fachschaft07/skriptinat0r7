package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;

/**
 * Repository to execute CRUD operations on {@code Lecture}s.
 */
public interface LectureRepository extends PagingAndSortingRepository<Lecture, Integer> {

    /**
     * Finds all {@code Lecture}s.
     *
     * @return a {@code List} of all lectures.
     */
    @Override
    Collection<Lecture> findAll();

}
