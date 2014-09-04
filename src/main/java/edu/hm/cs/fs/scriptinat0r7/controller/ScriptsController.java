package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController {

    private static final String SCRIPTS_LIST_VIEW = "scripts/list";
    private static final String SCRIPTS_SUBMIT_VIEW = "scripts/submit";
    @Autowired
    private ScriptRepository scripts;

    @Autowired
    private LectureRepository lectures;

    /**
     * Gets and displays all existing scripts.
     *
     * @param model
     *            the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getAllScripts(final ModelMap model) {
        model.addAttribute("scripts", scripts.findAll());
        return SCRIPTS_LIST_VIEW;
    }

    /**
     * Method responsible for rendering script submit page on get requests.
     * @return A model and a view for spring.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView submitScriptForm() {
        final ModelAndView modelAndView = new ModelAndView(SCRIPTS_SUBMIT_VIEW, "script", new Script());
        modelAndView.addObject("lectures", lectures.findAll());
        return modelAndView;
    }

    /**
     * Method responsible for persisting scripts in the database.
     * @param model the model used by the view.
     * @param script the user filled script instance.
     * @return the logical view name.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitScript(final ModelMap model, @ModelAttribute("script") final Script script) {
        scripts.save(script);
        model.addAttribute("submitted", true);
        model.addAttribute("script", new Script());
        model.addAttribute("lectures", lectures.findAll());
        return SCRIPTS_SUBMIT_VIEW;
    }

}
