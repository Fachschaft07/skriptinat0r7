package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.List;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;

public interface ProfessorRepository {

    List<Professor> findByFirstNameContainingOrLastNameContaining(String name);

}
