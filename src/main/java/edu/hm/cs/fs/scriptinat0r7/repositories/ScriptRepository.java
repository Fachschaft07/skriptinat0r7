package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;

/**
 * Repository to execute CRUD operations on {@code Script}s.
 */
public interface ScriptRepository extends PagingAndSortingRepository<Script, Integer> {

    /**
     * Finds all {@code Script}s.
     *
     * @return a {@code List} of all scripts.
     */
    @Override
    Collection<Script> findAll();

    /**
     * Returns all scripts, which have script documents with the given state.
     * @param states The states that filter the scripts.
     * @return a {@code List} of all matching scripts.
     */
    @Query("SELECT u FROM #{#entityName} u INNER JOIN u.scriptDocuments j where j.reviewState in (:states)")
    List<Script> findByReviewState(@Param("states") ReviewState... states);
    /* 
     * TODO: Query oben prüfen. Genommen und umgebaut von
     * http://stackoverflow.com/questions/19886903/
     * generation-query-when-the-manytomany-relationship-is-used-by-spring-data-jpa-pro
     */
}
