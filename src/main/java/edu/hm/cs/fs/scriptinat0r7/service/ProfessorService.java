package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professors;

    public List<Professor> findAll() {
        return professors.findAll(new Sort(Sort.Direction.ASC, "lastName"));
    }

    public Professor save(Professor professor) {
        return professors.save(professor);
    }

    public void delete(Professor professor) {
        professors.delete(professor);
    }


}
