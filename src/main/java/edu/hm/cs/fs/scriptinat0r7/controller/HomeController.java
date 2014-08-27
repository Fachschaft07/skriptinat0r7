package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the start page and other small pages (e.g. about page). *
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String index(final ModelMap model) {
        return "index";
    }

    /**
     * Request method for rendering the about page.
     * 
     * @param model
     *            the model to render.
     * @return the logical view name.
     */
    @RequestMapping("about")
    public String about(final ModelMap model) {
        List<String> contributors = new ArrayList<String>();
        contributors.add("Melanie Reiter");
        contributors.add("Fabian Trampusch");
        contributors.add("Maximilian Götz");

        model.addAttribute("contributors", contributors);
        model.addAttribute("year", Calendar.getInstance().get(Calendar.YEAR));
        return "about";
    }
}
