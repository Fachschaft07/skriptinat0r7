package edu.hm.cs.fs.scriptinat0r7.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ProfessorRepository;

/**
 * Controller to render pages for dealing with {@code Lecture}s.
 */
@Controller
@RequestMapping("/lectures")
public class LecturesController extends AbstractController {

    private static final String LECTURES_ADD_VIEW = "lectures/add";
    private static final String LECTURES_LIST_VIEW = "lectures/list";

    @Autowired
    private LectureRepository lectures;

    @Autowired
    private ProfessorRepository professors;

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

}
