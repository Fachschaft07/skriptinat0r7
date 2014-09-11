package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.repositories.LectureRepository;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptsService;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController extends AbstractController {

    private static final String SCRIPTS_LIST_VIEW = "scripts/list";
    private static final String SCRIPTS_LIST_PATH = "scripts";
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
                final Script savedScript = scripts.save(script);
                addSuccessFlash("Skript erfolgreich erstellt. Bitte fahre mit den Dateien fort.", redirectAttributes);
                return redirect("scripts/submit/files/" + savedScript.getId());
            } catch (DataAccessException e) {
                addErrorFlash("Es trat ein Fehler auf: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect(SCRIPTS_SUBMIT_VIEW);
            }
        }
    }

    @RequestMapping(value = "/submit/files/{id}", method = RequestMethod.GET)
    public String addScriptFilesForm(final ModelMap map,
            @PathVariable("id") final Script script,
            final RedirectAttributes redirectAttributes,
            final HttpServletRequest httpServletRequest) {
        // TODO: REMOVE DEBUG CODE!
        if (isScriptOwnedByUserAndNotYetFinished(script, httpServletRequest)) {
            map.addAttribute("id", script.getId());
            return "scripts/submit-files";
        } else {
            addErrorFlash("Das Skript wurde bereits vollständig abgeschickt.", redirectAttributes);
            return redirect(SCRIPTS_LIST_PATH);
        }
    }

    private boolean isScriptOwnedByUserAndNotYetFinished(final Script script,
            final HttpServletRequest httpServletRequest) {
        return true || (script.getSubmitter() == httpServletRequest.getUserPrincipal() && !script.isSubmittedCompletely());
    }

    @RequestMapping(value = "/submit/files/{id}", method = RequestMethod.POST)
    public String addScriptFilesSubmit(final ModelMap map,
            @PathVariable("id") int scriptId,
            @RequestParam("files[]") final List<MultipartFile> files,
            final RedirectAttributes redirectAttributes) {
        final Script script = scripts.
        try {
            for(int i = 0; i < files.size(); i++) {
                MultipartFile file = files.get(i);
                ScriptDocument document = new ScriptDocument();
                document.setFile(file.getBytes());
                document.setFilename(file.getName());
                document.setReviewState(ReviewState.LOCKED);
                document.setScript(script);
                document.setSortnumber(i);
                script.addScriptDocument(document);
            }

            scripts.save(script);
            return redirect("script/submit/password/" + script.getId());
        } catch (Exception e) {
            addErrorFlash("Fehler beim Upload der Datei.", redirectAttributes);
            return redirect("script/submit/files/" + script.getId());
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
