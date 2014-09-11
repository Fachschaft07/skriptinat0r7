package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;

/**
 * A service for business operations on lectures.
 */
@Service
public class LectureService {

    @Autowired
    private LectureRepository lectures;

    /**
     * Return all lectures.
     * @return a list of lectures.
     */
    public List<Lecture> findAll() {
        return lectures.findAll(sortByLectureAndProfessorName());
    }

    private Sort sortByLectureAndProfessorName() {
        return new Sort(new Order(Sort.Direction.ASC, "name"), new Order(Sort.Direction.ASC, "readingProfessor.lastName"));
    }

    /**
     * save a given lecture instance.
     * @param lecture the lecture to save.
     * @return the saved lecture instance, could have an updated id for example.
     */
    public Lecture save(final Lecture lecture) {
        return lectures.save(lecture);
    }

    /**
     * Deletes the given lecture.
     * @param lecture the lecture to delete.
     */
    public void delete(final Lecture lecture) {
        lectures.delete(lecture);
    }

    public List<Lecture> findByNameContaining(String searchQuery) {
        return lectures.findByNameContaining(searchQuery);
    }
}
