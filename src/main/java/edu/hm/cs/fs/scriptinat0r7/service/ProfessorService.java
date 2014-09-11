package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;

/**
 * A service for business operations on professors.
 */
@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professors;

    /**
     * Finds all professors.
     * @return a list of all professors.
     */
    public List<Professor> findAll() {
        return professors.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }

    /**
     * Saves a given professor instance.
     * @param professor the professor to save.
     * @return the saved professor, the id could be updated for example.
     */
    public Professor save(final Professor professor) {
        return professors.save(professor);
    }

    /**
     * deletes a given professor.
     * @param professor the professor to delete.
     */
    public void delete(final Professor professor) {
        professors.delete(professor);
    }


}