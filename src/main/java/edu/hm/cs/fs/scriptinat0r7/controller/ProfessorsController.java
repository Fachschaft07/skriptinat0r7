package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;

/**
 * Controller to render pages for dealing with {@code Professors}s.
 */
@Controller
@RequestMapping("/professors")
public class ProfessorsController extends AbstractController {

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

    /**
     * Used to display a form to submit a new professor instance.
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addProfessorForm(final ModelMap model) {
        model.put("professor", new Professor());
        return "professors/add";
    }

    /**
     * Used to save a professor instance and redirect to the professor list.
     *
     * @param model
     *            the model used by the view.
     * @param professor
     *            the professor to persist.
     * @return the logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProfessor(final ModelMap model, @ModelAttribute("professor") final Professor professor) {
        professor.setRole(Role.PROFESSOR);
        professors.save(professor);
        return redirect("professors");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProfessor(final ModelMap model, @PathVariable("id") final Integer id) {
        model.put("professor", professors.findOne(id));
        return "professors/edit";
    }
}
