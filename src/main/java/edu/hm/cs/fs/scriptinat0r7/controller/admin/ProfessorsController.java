package edu.hm.cs.fs.scriptinat0r7.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.ImmutableMap;

import edu.hm.cs.fs.scriptinat0r7.controller.AbstractController;
import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;

/**
 * Controller to render pages for dealing with {@code Professors}s.
 */
@Secured("ROLE_FACHSCHAFTLER")
@Controller
@RequestMapping("/professors")
public class ProfessorsController extends AbstractController {

    private static final String PROFESSOR_SAVED_MESSAGE = "Professor erfolgreich gespeichert";
    private static final String PROFESSOR_COULD_NOT_BE_SAVED_MESSAGE = "Professor konnte nicht gespeichert werden: ";
    private static final String PROFESSORS_ADD_VIEW = "professors/add";
    private static final String PROFESSORS_EDIT_VIEW = "professors/edit";

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private LectureService lecturesService;

    /**
     * Gets and displays all existing professors.
     *
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllScripts(final ModelMap model) {
        final List<ImmutableMap<String, Object>> professorsAndLectures = new ArrayList<>();

        for (final Professor professor : professorService.findAll()) {
            final String lectures = lecturesService.findByProfessor(professor)
                                        .stream()
                                        .map(lecture -> lecture.getName())
                                        .collect(Collectors.joining(", "));

            professorsAndLectures.add(ImmutableMap.<String, Object>builder()
                    .put("professor", professor)
                    .put("lectures", lectures)
                    .build());
        }

        model.addAttribute("professorsAndLectures", professorsAndLectures);
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
        model.addAttribute("professor", new Professor());
        return PROFESSORS_ADD_VIEW;
    }

    /**
     * Used to save a professor instance and redirect to the professor list.
     * @param model the model used by the view.
     * @param professor the professor to persist.
     * @param result The binding result from spring, it can contain errors about the model.
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @return the logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProfessorSubmit(final ModelMap model,
            @Valid @ModelAttribute("professor") final Professor professor,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("professor", professor);
            return PROFESSORS_ADD_VIEW;
        } else {
            try {
                professor.setRole(Role.PROFESSOR);
                professorService.save(professor);
                addSuccessFlash(PROFESSOR_SAVED_MESSAGE, redirectAttributes);
                return redirect("professors");
            } catch (final DataAccessException e) {
                addErrorFlash(PROFESSOR_COULD_NOT_BE_SAVED_MESSAGE + e.getLocalizedMessage(), redirectAttributes);
                return redirect(PROFESSORS_ADD_VIEW);
            }
        }
    }

    /**
     * Used to edit a existing professor instance.
     * @param model the model used by the view.
     * @param professor the professor which is going to be edited.
     * @return the logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProfessorForm(final ModelMap model, @PathVariable("id") final Professor professor) {
        professor.setLectures(lecturesService.findByProfessor(professor));
        model.addAttribute("professor", professor);
        return PROFESSORS_EDIT_VIEW;
    }

    /**
     * Used to persist edited attributes of a existing professor.
     * @param model the model used by the view.
     * @param professorToSave the professor, fetched from the db.
     * @param professorSubmitted The submitted professor instance, deserialized from a POST-Request. Can therefore be missing some attributes which were not included in the HTML.
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @param result The binding result from spring, it can contain errors about the model.
     * @return the logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editProfessorSubmit(final ModelMap model,
            @PathVariable("id") final Professor professorToSave,
            @Valid @ModelAttribute("professor") final Professor professorSubmitted,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return editProfessorForm(model, professorSubmitted);
        } else {
            try {
                // we copy the attributes, because we are missing some foreign
                // keys from the post request. (e.g. ordered scripts, which
                // every user has)
                professorToSave.setEmail(professorSubmitted.getEmail());
                professorToSave.setFirstName(professorSubmitted.getFirstName());
                professorToSave.setLastName(professorSubmitted.getLastName());
                professorToSave.setTitle(professorSubmitted.getTitle());
                professorService.save(professorToSave);
                addSuccessFlash(PROFESSOR_SAVED_MESSAGE, redirectAttributes);
                return redirect("professors");
            } catch (final DataAccessException e) {
                addErrorFlash(PROFESSOR_COULD_NOT_BE_SAVED_MESSAGE + e.getLocalizedMessage(), redirectAttributes);
                return redirect(PROFESSORS_EDIT_VIEW + "/" + professorSubmitted.getId());
            }
        }
    }

    /**
     * Used to delete professor instances.
     * @param professor The professor to delete.
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @return the logical view name.
     */
    @RequestMapping(value = "/delete/{id}") // TODO: method = delete!
    public String deleteProfessor(@ModelAttribute("professor") final Professor professor,
            final RedirectAttributes redirectAttributes) {
        try {
            professorService.delete(professor);
            addSuccessFlash("Professor erfolgreich gelöscht", redirectAttributes);
            return redirect("professors");
        } catch (final DataAccessException e) {
            addErrorFlash("Professor konnte nicht gelöscht werden: " + e.getLocalizedMessage(), redirectAttributes);
            return redirect(PROFESSORS_EDIT_VIEW + "/" + professor.getId());
        }
    }
}
