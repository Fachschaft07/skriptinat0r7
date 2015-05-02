package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;

/**
 * Repository to execute CRUD operations on {@code ScriptDocument}s.
 */
public interface ScriptDocumentRepository extends PagingAndSortingRepository<ScriptDocument, Long> {

    /**
     * Finds all {@code ScriptDocument}s.
     *
     * @return a {@code List} of all script documents.
     */
    @Override
    Collection<ScriptDocument> findAll();

    /**
     * Find all script documents and order them by the sortnumber.
     * @param script the script whose script documents shall be retrieved.
     * @return the script documents.
     */
    List<ScriptDocument> findByScriptsInOrderBySortnumberAsc(Script script);

    /**
     * Find all {@code ScriptDocument}s for a script, where the password is missing.
     * @param script the script whose script documents shall be retrieved.
     * @return the script documents.
     */
    List<ScriptDocument> findByScriptsInAndIsPasswordMissingTrue(Script script);

    Set<ScriptDocument> findByOrdersIn(StudentOrder order);

}
