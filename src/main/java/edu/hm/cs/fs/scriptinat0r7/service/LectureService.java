package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;

/**
 * A service for business operations on lectures.
 */
@Service
public class LectureService {

    @Autowired
    private LectureRepository lectures;

    @Autowired
    private ScriptService scriptsService;

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

    /**
     * Returns all lectures where the name contains the given query.
     * @param searchQuery the query.
     * @return all matching lectures.
     */
    public List<Lecture> findByNameContaining(final String searchQuery) {
        return lectures.findByNameContainingIgnoreCase(searchQuery);
    }

    /**
     * Returns all lectures which have a script in the given collection.
     * @param script the script, which is used to filter the lectures.
     * @return a list of matched lectures.
     */
    public List<Lecture> findByScript(final Script script) {
        return lectures.findByUsedScriptsIn(Collections.singleton(script));
    }

    public List<Lecture> findLecturesWithPublicScript() {
        final List<Script> findAllPublicScripts = scriptsService.findAllPublicScripts();
        if (findAllPublicScripts.isEmpty()) {
            return new ArrayList<Lecture>();
        } else {
            return lectures.findByUsedScriptsIn(findAllPublicScripts);
        }
    }

    public Lecture findOne(final Integer id) {
        return lectures.findOne(id);
    }

    public List<Lecture> findByProfessor(final Professor professor) {
        return lectures.findByReadingProfessor(professor, new Sort("name"));
    }
}
