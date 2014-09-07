package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * @param professor
     *            the professor to persist.
     * @return the logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProfessor(@ModelAttribute("professor") final Professor professor) {
        professor.setRole(Role.PROFESSOR);
        professors.save(professor);
        return redirect("professors");
    }

    /**
     * Used to edit a existing professor instance.
     * @param model the model used by the view.
     * @param id the id of the professor which is going to be edited.
     * @return the logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProfessorForm(final ModelMap model, @PathVariable("id") final Integer id) {
        model.put("professor", professors.findOne(id));
        return "professors/edit";
    }

    /**
     * Used to persist edited attributes of a existing professor.
     * @param professor The submitted professor instance, deserialized from a POST-Request. Can therefore be missing some attributes which were not included in the HTML.
     * @param id The id of the professor which is going to be edited.
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @return the logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editProfessor(@ModelAttribute("professor") final Professor professor,
            @PathVariable("id") final Integer id,
            final RedirectAttributes redirectAttributes) {
        Professor professorToSave = professors.findOne(id);
        professorToSave.setEmail(professor.getEmail());
        professorToSave.setFirstName(professor.getFirstName());
        professorToSave.setLastName(professor.getLastName());
        professorToSave.setTitle(professor.getTitle());
        professors.save(professorToSave);
        addSuccessFlash("Professor erfolgreich gespeichert", redirectAttributes);
        return redirect("professors");
    }
}
