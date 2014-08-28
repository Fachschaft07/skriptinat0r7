package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;

public interface ProfessorRepository extends PagingAndSortingRepository<Professor, Long> {

    @Query("select u from #{#entityName} u where u.firstName like %:name% or u.lastName like %:name%")
    List<Professor> findByFirstNameContainingOrLastNameContaining(@Param("name") String name);
}
