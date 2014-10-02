package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * A service for business operations on scripts.
 */
@Service
public class ScriptsService {

    @Autowired
    private ScriptRepository scriptsRepository;

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
        List<Script> scripts = scriptsRepository.findByReviewState(ReviewState.LOCKED);
        return scripts;
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
     * returns the given script or throws an exception if not all script documents are approved.
     * @param id the script to retrieve.
     * @return the script.
     * @throws UnauthorizedException thrown if there are script documents which are not accessible.
     */
    @Transactional
    public Script findPublicScriptById(final int id) throws UnauthorizedException {
        final Script script = scriptsRepository.findOne(id);
        if (script.areAllScriptsApproved()) {
            return script;
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * Persists a script and set it as finalized.
     * @param script the script to finalize.
     */
    public void finalizeScriptSubmit(final Script script) {
        script.setSubmittedCompletely(true);
        scriptsRepository.save(script);
    }
}
