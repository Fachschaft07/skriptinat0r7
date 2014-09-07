package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
        model.put("lectures", lectures.findAll());
        return LECTURES_LIST_VIEW;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addForm(final ModelMap model) {
        model.put("professors", professors.findAll());
        model.put("lecture", new Lecture());
        return "lectures/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ExceptionHandler
    public String addFormSubmit(final RedirectAttributes redirectAttributes, @ModelAttribute("lecture") final Lecture lecture, BindingResult errors) {
        if ( ! errors.hasErrors()) {
            lectures.save(lecture);
            addSuccessFlash("Die Vorlesung wurde erfolgreich angelegt", redirectAttributes);
            return redirect("lectures");
        } else {
            return redirect("lectures/add");
        }

    }

}
