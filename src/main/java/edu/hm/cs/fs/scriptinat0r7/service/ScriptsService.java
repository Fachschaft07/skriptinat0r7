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

@Service
public class ScriptsService {

    @Autowired
    private ScriptRepository scripts;

    public Collection<Script> findAll() {
        return scripts.findAll();
    }

    public List<Script> findAllPublicScripts() {
        return scripts.findByReviewState(ReviewState.FACHSCHAFTLERAPPROVED, ReviewState.PROFESSORAPPROVED);
    }

    public List<Script> findAllLockedScripts() {
        return scripts.findByReviewState(ReviewState.LOCKED);
    }

    /**
     * Saves a new script and rejects parameter intrusion.
     * @param script The script to save. Only certain fields are used. ID is for example ignored.
     * @return The saved script instance, containing for example the ID.
     */
    public Script saveAsNewScript(Script script) {
        Script scriptToSave = new Script();
        scriptToSave.setCategory(script.getCategory());
        scriptToSave.setLectures(script.getLectures());
        scriptToSave.setName(script.getName());
        scriptToSave.setSubmittedCompletely(false);
        scriptToSave.setSubmitter(script.getSubmitter());
        return scripts.save(scriptToSave);
    }

    public Script findOne(int id) {
        return scripts.findOne(id);
    }

    @Transactional
    public Script findPublicScriptById(int id) throws UnauthorizedException {
        final Script script = scripts.findOne(id);
        if (script.areAllScriptsApproved()) {
            return script;
        } else {
            throw new UnauthorizedException();
        }
    }
}
