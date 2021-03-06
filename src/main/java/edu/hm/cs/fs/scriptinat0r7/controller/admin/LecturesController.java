package edu.hm.cs.fs.scriptinat0r7.controller.admin;

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

import edu.hm.cs.fs.scriptinat0r7.controller.AbstractController;
import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;

/**
 * Controller to render pages for dealing with {@code Lecture}s.
 */
@Secured("ROLE_FACHSCHAFTLER")
@Controller
@RequestMapping("/lectures")
public class LecturesController extends AbstractController {

    private static final String LECTURES_ADD_VIEW = "lectures/add";
    private static final String LECTURES_LIST_VIEW = "lectures/list";

    @Autowired
    private LectureService lectures;

    @Autowired
    private ProfessorService professors;

    /**
     * Gets and displays all existing lectures.
     *
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping
    public String list(final ModelMap model) {
        model.addAttribute("lectures", lectures.findAll());
        return LECTURES_LIST_VIEW;
    }

    /**
     * Displays form to submit a new lecture.
     * @param model The model map.
     * @return The logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addLectureForm(final ModelMap model) {
        model.addAttribute("professors", professors.findAll());
        model.addAttribute("lecture", new Lecture());
        return LECTURES_ADD_VIEW;
    }

    /**
     * Handles a post request for a new lecture.
     * @param redirectAttributes Injected by spring, used to render flash messages.
     * @param model the model used by the view.
     * @param lecture The submitted lecture.
     * @param result The binding result from spring, it can contain errors about the model.
     * @return The logical view name.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addLectureSubmit(final RedirectAttributes redirectAttributes,
            final ModelMap model,
            @Valid @ModelAttribute("lecture") final Lecture lecture,
            final BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("professors", professors.findAll());
            model.addAttribute("lecture", lecture);
            return LECTURES_ADD_VIEW;
        } else {
            lectures.save(lecture);
            addSuccessFlash("Die Vorlesung wurde erfolgreich angelegt", redirectAttributes);
            return redirect("lectures");
        }
    }

    /**
     * Displays an edit form for a given lecture instance.
     * @param model the model map.
     * @param lecture the lecture to edit.
     * @return a logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLectureForm(final ModelMap model, @PathVariable("id") final Lecture lecture) {
        model.addAttribute("lecture", lecture);
        model.addAttribute("professors", professors.findAll());
        return "lectures/edit";
    }

    /**
     * Handles a post request to edit a given lecture.
     * @param model the model map.
     * @param lectureToSave the lecture to save.
     * @param lectureSubmitted the lecture submitted via POST.
     * @param result the binding result, which gives informations about POST-errors
     * @param redirectAttributes redirect attributes for e.g. flash messages
     * @return a logical view name.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editLectureSubmit(final ModelMap model,
            @PathVariable("id") final Lecture lectureToSave,
            @Valid @ModelAttribute("lecture") final Lecture lectureSubmitted,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return editLectureForm(model, lectureSubmitted);
        } else {
            try {
                lectureToSave.setName(lectureSubmitted.getName());
                lectureToSave.setReadingProfessor(lectureSubmitted.getReadingProfessor());
                lectureToSave.setStudyProgram(lectureSubmitted.getStudyProgram());
                lectures.save(lectureToSave);
                addSuccessFlash("Vorlesung erfolgreich gespeichert.", redirectAttributes);
                return redirect("lectures");
            } catch (final DataAccessException e) {
                addErrorFlash("Vorlesung konnte nicht gespeichert werden: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect("lectures/edit/" + lectureSubmitted.getId());
            }
        }
    }

    /**
     * Delete a lecture via the id.
     * @param lecture The lecture to delete.
     * @param redirectAttributes redirect attributes for e.g. flash messages
     * @return a logical view name
     */
    @RequestMapping(value = "/delete/{id}") // TODO: Method = delete!
    public String deleteLecture(@PathVariable("id") final Lecture lecture, final RedirectAttributes redirectAttributes) {
        try {
            lectures.delete(lecture);
            addSuccessFlash("Vorlesung erfolgreich gelöscht", redirectAttributes);
            return redirect("lectures");
        } catch (final DataAccessException e) {
            addErrorFlash("Vorlesung konnte nicht gelöscht werden: " + e.getLocalizedMessage(), redirectAttributes);
            return redirect("lectures/edit/" + lecture.getId());
        }
    }
}
