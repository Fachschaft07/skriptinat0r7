package edu.hm.cs.fs.scriptinat0r7.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public String addProfessor(final ModelMap model,
            @Valid @ModelAttribute("professor") final Professor professor,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.put("professor", professor);
            return "professors/add";
        } else {
            try {
                professor.setRole(Role.PROFESSOR);
                professors.save(professor);
                return redirect("professors");
            } catch (DataAccessException e) {
                addErrorFlash("Professor konnte nicht gespeichert werden: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect("professors/add");
            }
        }
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
    public String editProfessor(final ModelMap model,
            @Valid @ModelAttribute("professor") final Professor professor,
            final BindingResult result,
            @PathVariable("id") final Professor professorToSave,
            final RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            model.put("professor", professor);
            return "professors/edit";
        } else {
            try {
                professorToSave.setEmail(professor.getEmail());
                professorToSave.setFirstName(professor.getFirstName());
                professorToSave.setLastName(professor.getLastName());
                professorToSave.setTitle(professor.getTitle());
                professors.save(professorToSave);
                addSuccessFlash("Professor erfolgreich gespeichert", redirectAttributes);
                return redirect("professors");
            } catch (DataAccessException e) {
                addErrorFlash("Professor konnte nicht gespeichert werden: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect("professors/edit/" + professor.getId());
            }
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteProfessor(@ModelAttribute("professor") final Professor professor,
            final RedirectAttributes redirectAttributes) {
        try {
            professors.delete(professor);
            addSuccessFlash("Professor erfolgreich gelöscht", redirectAttributes);
            return redirect("professors");
        } catch (DataAccessException e) {
            addErrorFlash("Professor konnte nicht gelöscht werden: " + e.getLocalizedMessage(), redirectAttributes);
            return redirect("professors/edit/" + professor.getId());
        }
    }
}
