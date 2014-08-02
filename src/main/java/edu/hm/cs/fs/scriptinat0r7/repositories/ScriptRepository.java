package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Script;

public interface ScriptRepository extends PagingAndSortingRepository<Script, Long> {

    List<Script> findByNameLike(String name);

}
