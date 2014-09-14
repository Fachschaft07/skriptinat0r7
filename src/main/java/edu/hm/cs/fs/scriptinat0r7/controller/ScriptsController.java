package edu.hm.cs.fs.scriptinat0r7.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import com.google.common.collect.Sets;

import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;
import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentsService;
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
    private ScriptsService scriptsService;

    @Autowired
    private LectureService lecturesService;

    @Autowired
    private ScriptDocumentsService documentsService;

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
            model.addAttribute("scripts", scriptsService.findAll());
        } else {
            model.addAttribute("scripts", scriptsService.findAllPublicScripts());
        }
        return SCRIPTS_LIST_VIEW;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showScriptDetail(final ModelMap model,
            @PathVariable("id") final int id,
            final RedirectAttributes redirectAttribtues) throws UnauthorizedException {
        model.addAttribute("script", scriptsService.findPublicScriptById(id));
        return "scripts/detail";
    }

    /**
     * Method responsible for rendering script submit page on get requests.
     * @return A model and a view for spring.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public String addScriptForm(final ModelMap model) {
        model.addAttribute("lectures", lecturesService.findAll());
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
            model.addAttribute("lectures", lecturesService.findAll());
            model.addAttribute("script", script);
            return SCRIPTS_SUBMIT_VIEW;
        } else {
            try {
                final Script savedScript = scriptsService.saveAsNewScript(script);
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
            final HttpServletRequest httpServletRequest) throws UnauthorizedException {
        abortUnauthorizedAccess(script, httpServletRequest);
        map.addAttribute("id", script.getId());
        return "scripts/submit-files";
    }

    private void abortUnauthorizedAccess(final Script script,
            final HttpServletRequest httpServletRequest) throws UnauthorizedException {
        // TODO: maybe move to service layer
        // TODO: is it consequently used?
        if (!(true || (script.getSubmitter() == httpServletRequest.getUserPrincipal() && !script.isSubmittedCompletely()))) {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/submit/files/{id}", method = RequestMethod.POST)
    public String addScriptFilesSubmit(final ModelMap map,
            @PathVariable("id") final Script script,
            @RequestParam("files[]") final List<MultipartFile> files,
            final RedirectAttributes redirectAttributes,
            final HttpServletRequest httpServletRequest) throws UnauthorizedException {
        abortUnauthorizedAccess(script, httpServletRequest);
        final List<String> filesInError = new LinkedList<>();
        for(int i = 0; i < files.size(); i++) {
            final MultipartFile file = files.get(i);
            try {
                documentsService.save(script, i, file);
            } catch (DataAccessException | IOException e) {
                // TODO logging
                filesInError.add(file.getOriginalFilename());
            }
        }

        if (!filesInError.isEmpty()) {
            final String message = "Folgende Dateien konnten nicht erfolgreich hochgeladen werden: " + StringUtils.join(filesInError, ", ");
            addErrorFlash(message, redirectAttributes);
        }

        return redirect("scripts/submit/password/" + script.getId());
    }

    @RequestMapping("submit/password/{id}")
    public String addScriptPasswordForm(@PathVariable("id") final Script script,
            final ModelMap model,
            final HttpServletRequest httpServletRequest,
            @RequestParam(value = "passwords", defaultValue = "") String passwordsPost,
            final RedirectAttributes redirectAttributes) throws UnauthorizedException {
        abortUnauthorizedAccess(script, httpServletRequest);
        final List<String> passwords = new LinkedList<>();
        Collections.addAll(passwords, StringUtils.split(passwordsPost));
        final List<ScriptDocument> documentsWithMissingPassword = documentsService.tryPasswordsOnScriptDocumentsWithMissingPassword(script, passwords);
        final List<ScriptDocument> documentsWithKnownPassword = documentsService.findByScript(script);
        documentsWithKnownPassword.removeAll(documentsWithMissingPassword);

        if (!documentsWithMissingPassword.isEmpty()) {
            model.addAttribute("id", script.getId());
            model.addAttribute("documentsWithMissingPassword", documentsWithMissingPassword);
            model.addAttribute("documentsWithKnownPassword", documentsWithKnownPassword);
            return "scripts/submit-password";
        } else {
            return redirect("scripts/submit/summarize/" + script.getId());
        }
    }

    @RequestMapping(value = "/submit/summarize/{id}", method = RequestMethod.GET)
    public String addScriptSummarizeForm(final ModelMap model,
            @PathVariable("id") final Script script,
            final HttpServletRequest httpServletRequest) throws UnauthorizedException {
        abortUnauthorizedAccess(script, httpServletRequest);
        List<Lecture> scriptLectures = lecturesService.findByScript(script);
        script.setLectures(Sets.newHashSet(scriptLectures)); // TODO: horrible hack to avoid lazy loading exception in view
        model.addAttribute("script", script);
        model.addAttribute("scriptDocuments", documentsService.findByScript(script));
        model.addAttribute("lectures", scriptLectures);
        return "scripts/submit-summarize";
    }

    @RequestMapping(value = "/submit/summarize/{id}", method = RequestMethod.POST)
    public String addScriptSummarizeSubmit(final RedirectAttributes redirectAttributes,
            @RequestParam("scriptDocumentSort[]") final String[] orderedDocumentHashes,
            @PathVariable("id") final Script script,
            HttpServletRequest httpServletRequest) throws UnauthorizedException {
        abortUnauthorizedAccess(script, httpServletRequest);

        documentsService.updateDocumentOrder(mapStringArrayToLongList(orderedDocumentHashes),
                documentsService.findByScript(script));
        scriptsService.finalizeScriptSubmit(script);

        addSuccessFlash("Script erfolgreich eingeschickt. Herzlichen Dank für deine Mühe. Über deinen Account kannst du zu deinen Einsendungen navigieren. Dort siehst du, sobald es zur Bestellung freigeschalten wurde.", redirectAttributes);
        return redirect("scripts");
    }

    private List<Long> mapStringArrayToLongList(final String[] orderedDocumentHashes) {
        // TODO: java 8 lambda
        List<Long> result = new LinkedList<Long>();
        for (final String hash : orderedDocumentHashes) {
            result.add(Long.parseLong(hash));
        }
        return result;
    }

    /**
     * Show all submissions.
     * @param model the model used by the view.
     * @return the logical view name.
     */
    @RequestMapping(value = "/show-submissions", method = RequestMethod.GET)
    public String showScriptSubmissions(final ModelMap model) {
        model.addAttribute("scripts", scriptsService.findAllLockedScripts());
        return "scripts/show-submissions";
    }
}
