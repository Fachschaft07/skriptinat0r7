package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.repositories.ScriptRepository;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController extends AbstractController {

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
        model.addAttribute("scripts", scripts.findByReviewState(ReviewState.FACHSCHAFTLERAPPROVED, ReviewState.PROFESSORAPPROVED));
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
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @return the logical view name.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitScript(final ModelMap model, @ModelAttribute("script") final Script script,
            final RedirectAttributes redirectAttributes) {
        scripts.save(script);
        addSuccessFlash("Ihr Skript wurde erfolgreich hochgeladen. Wir werden es reviewen und gegebenenfalls zum Druck bereit stellen.", redirectAttributes);
        return redirect(SCRIPTS_SUBMIT_VIEW);
    }

    @RequestMapping(value = "/show-submissions", method = RequestMethod.GET)
    public String showScriptSubmissions(final ModelMap model) {
        model.put("scripts", scripts.findByReviewState(ReviewState.LOCKED));
        return "scripts/show-submissions";
    }

    @RequestMapping(value = "/show-all", method = RequestMethod.GET)
    public String showAllScripts(final ModelMap model) {
        model.put("scripts", scripts.findAll());
        return "scripts/show-all";
    }
}
