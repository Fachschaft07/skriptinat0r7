package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController {

    @Autowired
    private ScriptRepository scripts;

    /**
     * Gets and displays all existing scripts.
     *
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllScripts(final ModelMap model) {
        model.addAttribute("scripts", scripts.findAll());
        return "scripts/list";
    }
}
