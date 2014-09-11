package edu.hm.cs.fs.scriptinat0r7.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Script save(Script script) {
        return scripts.save(script);
    }

    @Transactional
    public Script findPublicScriptById(int id) throws IllegalAccessException {
        final Script script = scripts.findOne(id);
        if (script.areAllScriptsApproved()) {
            return script;
        } else {
            throw new IllegalAccessException("Script not public");
        }
    }
}
