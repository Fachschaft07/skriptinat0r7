package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptController {

    @Autowired
    private ScriptRepository scripts;
    
    @RequestMapping(method = RequestMethod.GET)
    public String getAllScripts(ModelMap model) {
        Iterable<Script> allScripts = scripts.findAll();
        model.addAttribute("scripts", allScripts);
        
        return "allScripts";
    }
}
