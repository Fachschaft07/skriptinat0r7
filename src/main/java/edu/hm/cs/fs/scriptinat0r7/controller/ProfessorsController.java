package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;

/**
 * Controller to render pages for dealing with {@code Professors}s.
 */
@Controller
@RequestMapping("/professors")
public class ProfessorsController {

    @Autowired
    private ProfessorRepository professors;

    /**
     * Gets and displays all existing professors.
     *
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllScripts(final ModelMap model) {
        model.addAttribute("professors", professors.findAll());
        return "professors/list";
    }
}
