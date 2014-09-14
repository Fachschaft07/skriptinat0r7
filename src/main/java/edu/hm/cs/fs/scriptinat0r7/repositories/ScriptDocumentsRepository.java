package edu.hm.cs.fs.scriptinat0r7.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;

/**
 * Repository to execute CRUD operations on {@code ScriptDocument}s.
 */
public interface ScriptDocumentsRepository extends PagingAndSortingRepository<ScriptDocument, Long> {

    /**
     * Finds all {@code ScriptDocument}s.
     *
     * @return a {@code List} of all script documents.
     */
    @Override
    Collection<ScriptDocument> findAll();

    List<ScriptDocument> findByScriptOrderBySortnumberAsc(Script script);

    List<ScriptDocument> findByScriptAndIsPasswordMissingTrue(Script script);

}
