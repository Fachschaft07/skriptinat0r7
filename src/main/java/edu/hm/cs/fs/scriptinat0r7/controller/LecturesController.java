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

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;

/**
 * Controller to render pages for dealing with {@code Lecture}s.
 */
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

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editLectureForm(final ModelMap model, final @PathVariable("id") Lecture lecture) {
        model.addAttribute("lecture", lecture);
        model.addAttribute("professors", professors.findAll());
        return "lectures/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editLectureSubmit(final ModelMap model,
            final @PathVariable("id") Lecture lectureToSave,
            @Valid @ModelAttribute("lecture") final Lecture lectureSubmitted,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return editLectureForm(model, lectureSubmitted);
        } else {
            try {
                lectureToSave.setName(lectureSubmitted.getName());
                lectureToSave.setReadingProfessor(lectureSubmitted.getReadingProfessor());
                lectureToSave.setSemesterType(lectureSubmitted.getSemesterType());
                lectureToSave.setSemesterYear(lectureSubmitted.getSemesterYear());
                lectureToSave.setStudyProgram(lectureSubmitted.getStudyProgram());
                lectures.save(lectureToSave);
                addSuccessFlash("Vorlesung erfolgreich gespeichert.", redirectAttributes);
                return redirect("lectures");
            } catch (DataAccessException e) {
                addErrorFlash("Vorlesung konnte nicht gespeichert werden: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect("lectures/edit/" + lectureSubmitted.getId());
            }
        }
    }

    @RequestMapping(value = "/delete/{id}") // TODO: Method = delete!
    public String deleteLecture(final @PathVariable("id") Lecture lecture, RedirectAttributes redirectAttributes) {
        try {
            lectures.delete(lecture);
            addSuccessFlash("Vorlesung erfolgreich gelöscht", redirectAttributes);
            return redirect("lectures");
        } catch (DataAccessException e) {
            addErrorFlash("Vorlesung konnte nicht gelöscht werden: " + e.getLocalizedMessage(), redirectAttributes);
            return redirect("lectures/edit/" + lecture.getId());
        }
    }
}
