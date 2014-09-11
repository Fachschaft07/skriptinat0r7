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
     * Finds a {@code Script} by its name.
     *
     * @param name
     *            the name of the {@code Script} to search.
     * @return a {@code List} of all scripts matching the given name.
     */
    List<Script> findByNameContaining(String name);

    /**
     * Finds all {@code Script}s.
     *
     * @return a {@code List} of all scripts.
     */
    @Override
    Collection<Script> findAll();

    /**
     * Returns all sripts, which have script documents with the given state.
     * @param states The states that filter the scripts.
     * @return a {@code List} of all matching scripts.
     */
    @Query("SELECT u FROM #{#entityName} u"
            + " WHERE EXISTS (SELECT scriptDocument"
            + "              FROM ScriptDocument scriptDocument"
            + "              WHERE scriptDocument.reviewState in (:states)"
            + "              AND scriptDocument.script = u)")
    List<Script> findByReviewState(@Param("states") ReviewState... states);

}
