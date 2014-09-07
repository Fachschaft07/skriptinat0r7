package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;

/**
 * Controller to render pages for dealing with {@code Lecture}s.
 */
@Controller
@RequestMapping("/lectures")
public class LecturesController extends AbstractController {

    @Autowired
    private LectureRepository lectures;

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
        return "lectures/list";
    }

}
