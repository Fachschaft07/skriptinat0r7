package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for rendering the about page.
 */
@Controller
@RequestMapping("/about")
public class AboutController extends AbstractController {

    /**
     * Request method for rendering the about page.
     *
     * @param model
     *            the model to render.
     * @return the logical view name.
     */
    @RequestMapping
    public String about(final ModelMap model) {
        final List<String> contributors = new ArrayList<String>();
        contributors.add("Melanie Reiter");
        contributors.add("Fabian Trampusch");
        contributors.add("Maximilian Götz");

        model.addAttribute("contributors", contributors);
        model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        return "about/about";
    }

}
