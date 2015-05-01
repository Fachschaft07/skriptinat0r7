package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * A service for business operations on scripts.
 */
@Service
public class ScriptService {

    @Autowired
    private ScriptRepository scriptsRepository;

    @Autowired
    private ScriptDocumentService scriptDocumentsService;

    /**
     * Returns all scripts.
     * @return all scripts.
     */
    public Collection<Script> findAll() {
        return scriptsRepository.findAll();
    }

    /**
     * returns all public scripts.
     * @return all public scripts.
     */
    public List<Script> findAllPublicScripts() {
        return scriptsRepository.findByReviewState(ReviewState.FACHSCHAFTLERAPPROVED, ReviewState.PROFESSORAPPROVED);
    }

    /**
     * returns all locked scripts.
     * @return all locked scripts.
     */
    public List<Script> findAllLockedScripts() {
        return scriptsRepository.findByReviewState(ReviewState.LOCKED);
    }

    /**
     * Saves a new script and rejects parameter intrusion.
     * @param script The script to save. Only certain fields are used. ID is for example ignored.
     * @return The saved script instance, containing for example the ID.
     */
    public Script create(final Script script) {
        final Script scriptToSave = new Script();
        scriptToSave.setCategory(script.getCategory());
        scriptToSave.setLectures(script.getLectures());
        scriptToSave.setName(script.getName());
        scriptToSave.setSubmittedCompletely(false);
        scriptToSave.setSubmitter(script.getSubmitter());
        return scriptsRepository.save(scriptToSave);
    }

    /**
     * returns the script with the given id.
     * @param id the id of the script.
     * @return the script with the given id.
     */
    public Script findOne(final int id) {
        return scriptsRepository.findOne(id);
    }

    /**
     * Persists a script and set it as finalized.
     * @param script the script to finalize.
     */
    public void finalizeScriptSubmit(final Script script) {
        if (scriptDocumentsService.findByScript(script).isEmpty()) {
            throw new IllegalArgumentException("Can't persist script " + script.getId() + " because it does not has any documents.");
        }

        script.setSubmittedCompletely(true);
        scriptsRepository.save(script);
    }

    public Set<Script> findByLecture(final Lecture lecture) {
        return scriptsRepository.findByLecturesIn(lecture);
    }

    public Set<Script> findPublicByLecture(final Lecture lecture) {
        return findByLecture(lecture)
                .stream()
                .map(script -> { script.setScriptDocuments(scriptDocumentsService.findByScript(script)); return script; })
                .filter(script -> script.hasPublicDocuments())
                .collect(Collectors.toSet());
    }
}
