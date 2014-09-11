package edu.hm.cs.fs.scriptinat0r7.controller;

import javax.servlet.http.HttpServletRequest;
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

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptsService;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController extends AbstractController {

    private static final String SCRIPTS_LIST_VIEW = "scripts/list";
    private static final String SCRIPTS_SUBMIT_VIEW = "scripts/submit";

    @Autowired
    private ScriptsService scripts;

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
    public String showScripts(final ModelMap model, final HttpServletRequest request) {
        if (request.isUserInRole("ROLE_FACHSCHAFTLER")) {
            model.addAttribute("scripts", scripts.findAll());
        } else {
            model.addAttribute("scripts", scripts.findAllPublicScripts());
        }
        return SCRIPTS_LIST_VIEW;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showScriptDetail(final ModelMap model,
            @PathVariable("id") final int id,
            final RedirectAttributes redirectAttribtues) {
        try {
            model.addAttribute("script", scripts.findPublicScriptById(id));
            return "scripts/detail";
        } catch (IllegalAccessException e) {
            addErrorFlash("Script nicht verfügbar", redirectAttribtues);
            return redirect("scripts");
        }
    }

    /**
     * Method responsible for rendering script submit page on get requests.
     * @return A model and a view for spring.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String addScriptForm(final ModelMap model) {
        model.addAttribute("lectures", lectures.findAll());
        model.addAttribute("script", new Script());
        return SCRIPTS_SUBMIT_VIEW;
    }

    /**
     * Method responsible for persisting scripts in the database.
     * @param model the model used by the view.
     * @param script the user filled script instance.
     * @param redirectAttributes Redirect attributes, e.g. flash messages.
     * @return the logical view name.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String addScriptSubmit(final ModelMap model,
            @Valid @ModelAttribute("script") final Script script,
            final BindingResult result,
            final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("lectures", lectures.findAll());
            model.addAttribute("script", script);
            return SCRIPTS_SUBMIT_VIEW;
        } else {
            try {
                scripts.save(script);
                addSuccessFlash("Ihr Skript wurde erfolgreich hochgeladen. Wir werden es reviewen und gegebenenfalls zum Druck bereit stellen.", redirectAttributes);
                return redirect(SCRIPTS_SUBMIT_VIEW);
            } catch (DataAccessException e) {
                addErrorFlash("Es trat ein Fehler auf: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect(SCRIPTS_SUBMIT_VIEW);
            }
        }
    }

    /**
     * Show all submissions.
     * @param model the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(value = "/show-submissions", method = RequestMethod.GET)
    public String showScriptSubmissions(final ModelMap model) {
        model.addAttribute("scripts", scripts.findAllLockedScripts());
        return "scripts/show-submissions";
    }
}
