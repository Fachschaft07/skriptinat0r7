package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the index page.
 */
@Controller
@RequestMapping("/")
public class IndexController {

    /**
     * Request method for the index page.
     *
     * @param model
     *            the model to render.
     * @return the logical view name.
     */
    @RequestMapping
    public String index(final ModelMap model) {
        return "index";
    }
}
