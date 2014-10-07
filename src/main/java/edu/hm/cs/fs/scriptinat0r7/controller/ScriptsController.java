package edu.hm.cs.fs.scriptinat0r7.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.annotation.Secured;
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
import edu.hm.cs.fs.scriptinat0r7.model.Professor;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;
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
    private static final Logger LOGGER = Logger.getLogger(ScriptsController.class);

    @Autowired
    private ScriptsService scriptsService;

    @Autowired
    private LectureService lecturesService;

    @Autowired
    private ScriptDocumentsService documentsService;

    @Autowired
    private ProfessorService professorService;

    /**
     * Gets and displays all existing scripts.
     *
     * @param model
     *            the model used by the view.
     * @param request
     *            the http request.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showScripts(final ModelMap model, final HttpServletRequest request) {
        model.addAttribute("scripts", scriptsService.findAllPublicScripts());
        return SCRIPTS_LIST_VIEW;
    }

    /**
     * Shows a detail page for one script.
     * @param model the model used by the view.
     * @param id the script id.
     * @return the logical view name
     * @throws UnauthorizedException thrown when the user is not authorized to view the script.
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showScriptDetail(final ModelMap model,
            @PathVariable("id") final int id) throws UnauthorizedException {
        final Script script = scriptsService.findOne(id);
        model.addAttribute("script", script);
        model.addAttribute("documents", documentsService.findByScript(script));
        List<Lecture> lectures = lecturesService.findByScript(script);
        for (Lecture lecture : lectures) {
            Professor professor = professorService.findByLecture(lecture);
            lecture.setReadingProfessor(professor);
        }
        model.addAttribute("lectures", lectures);
        return "scripts/detail";
    }

    /**
     * Method responsible for rendering script submit page on get requests.
     * @param model the model used by the view.
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
     * @param result The binding result from spring, it can contain errors about the model.
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
                script.setSubmitter(getCurrentUser());
                final Script savedScript = scriptsService.create(script);
                return redirect("scripts/submit/files/" + savedScript.getId());
            } catch (DataAccessException e) {
                LOGGER.error("data access exception while trying to save a new script", e);
                addErrorFlash("Es trat ein Fehler auf: " + e.getLocalizedMessage(), redirectAttributes);
                return redirect(SCRIPTS_SUBMIT_VIEW);
            }
        }
    }

    /**
     * Method responsible for rendering a form to add files to a script.
     * @param map The model used by the view.
     * @param script the script to which the files shall be added.
     * @return the logical view name.
     * @throws UnauthorizedException Thrown, if the user is not authorized to add files to this script.
     */
    @RequestMapping(value = "/submit/files/{id}", method = RequestMethod.GET)
    public String addScriptFilesForm(final ModelMap map,
            @PathVariable("id") final Script script) throws UnauthorizedException {
        abortUnauthorizedAccess(script);
        map.addAttribute("id", script.getId());
        return "scripts/submit-files";
    }

    private void abortUnauthorizedAccess(final Script script) throws UnauthorizedException {
        final String currentUserName = getCurrentUser().getUsername();
        final String scriptSubmitterName = script.getSubmitter().getUsername();
        if (!scriptSubmitterName.equals(currentUserName) || script.isSubmittedCompletely()) {
            LOGGER.error("user " + currentUserName + " tried to submit a script"
                    + " for which he has no permissions or which is already submitted completely");
            throw new UnauthorizedException();
        }
    }

    /**
     * Method responsible for handling the upload of script documents.
     * @param script the script to which the documents shall be added.
     * @param files the files to add to the script.
     * @param redirectAttributes Injected by spring, used to render flash messages.
     * @return the logical view name.
     * @throws UnauthorizedException thrown if the user is not authorized to add files to this script.
     */
    @RequestMapping(value = "/submit/files/{id}", method = RequestMethod.POST)
    public String addScriptFilesSubmit(@PathVariable("id") final Script script,
            @RequestParam("files[]") final List<MultipartFile> files,
            final RedirectAttributes redirectAttributes) throws UnauthorizedException {
        abortUnauthorizedAccess(script);
        final List<String> filesInError = new LinkedList<>();
        for (int i = 0; i < files.size(); i++) {
            final MultipartFile file = files.get(i);
            try {
                // TODO: handle duplicates
                documentsService.create(Collections.singleton(script), i, file);
            } catch (DataAccessException | IOException | IllegalArgumentException e) {
                LOGGER.error("could not add file " + file.getName(), e);
                filesInError.add(file.getOriginalFilename());
            }
        }

        if (!filesInError.isEmpty()) {
            final String message = "Folgende Dateien konnten nicht erfolgreich hochgeladen werden: " + StringUtils.join(filesInError, ", ");
            addErrorFlash(message, redirectAttributes);
        }

        // TODO: no file successfully uploaded

        return redirect("scripts/submit/password/" + script.getId());
    }

    /**
     * Method used to ernder the password form.
     * @param script the script for which passwords shall be retrieved.
     * @param model the model used by the view.
     * @param passwordsPost the passwords submitted by the user.
     * @return the logical view name.
     * @throws UnauthorizedException thrown if the user is not allowed to add passwords.
     */
    @RequestMapping("submit/password/{id}")
    public String addScriptPasswordForm(@PathVariable("id") final Script script,
            final ModelMap model,
            @RequestParam(value = "passwords", defaultValue = "") final String passwordsPost) throws UnauthorizedException {
        abortUnauthorizedAccess(script);
        final List<String> passwords = new LinkedList<>();
        Collections.addAll(passwords, StringUtils.split(passwordsPost));
        final List<ScriptDocument> documentsWithMissingPassword = documentsService.tryPasswordsOnScriptDocumentsWithMissingPassword(script, passwords);
        final List<ScriptDocument> documentsWithKnownPassword = documentsService.findByScript(script);
        documentsWithKnownPassword.removeAll(documentsWithMissingPassword);

        if (documentsWithMissingPassword.isEmpty()) {
            return redirect("scripts/submit/summarize/" + script.getId());
        } else {
            model.addAttribute("id", script.getId());
            model.addAttribute("documentsWithMissingPassword", documentsWithMissingPassword);
            model.addAttribute("documentsWithKnownPassword", documentsWithKnownPassword);
            return "scripts/submit-password";
        }
    }

    /**
     * Renders the summarize form.
     * @param model the model used by the view.
     * @param script the script which is being summarized.
     * @return the logical view name.
     * @throws UnauthorizedException thrown if the user is not allowed to view this page.
     */
    @RequestMapping(value = "/submit/summarize/{id}", method = RequestMethod.GET)
    public String addScriptSummarizeForm(final ModelMap model,
            @PathVariable("id") final Script script) throws UnauthorizedException {
        abortUnauthorizedAccess(script);
        final List<Lecture> scriptLectures = lecturesService.findByScript(script);
        script.setLectures(Sets.newHashSet(scriptLectures)); // TODO: horrible hack to avoid lazy loading exception in view
        model.addAttribute("script", script);
        model.addAttribute("scriptDocuments", documentsService.findByScript(script));
        model.addAttribute("lectures", scriptLectures);
        return "scripts/submit-summarize";
    }

    /**
     * Method used to finalize a script and order script documents.
     * @param redirectAttributes Injected by spring, used to render flash messages.
     * @param orderedDocumentHashes hashes of the script documents, ordered by the user.
     * @param script the script which is being summarized.
     * @return a logical view name.
     * @throws UnauthorizedException thrown, if the user is not allowed to view this page.
     */
    @RequestMapping(value = "/submit/summarize/{id}", method = RequestMethod.POST)
    public String addScriptSummarizeSubmit(final RedirectAttributes redirectAttributes,
            @RequestParam("scriptDocumentSort[]") final String[] orderedDocumentHashes,
            @PathVariable("id") final Script script) throws UnauthorizedException {
        abortUnauthorizedAccess(script);

        documentsService.updateDocumentOrder(mapStringArrayToLongList(orderedDocumentHashes),
                documentsService.findByScript(script));
        scriptsService.finalizeScriptSubmit(script);

        addSuccessFlash("Skript erfolgreich eingeschickt. Herzlichen Dank für deine Mühe."
                + " Über deinen Account kannst du zu deinen Einsendungen navigieren."
                + " Dort siehst du, sobald es zur Bestellung freigeschalten wurde.", redirectAttributes);
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
    @Secured("ROLE_FACHSCHAFTLER")
    @RequestMapping(value = "/show-submissions", method = RequestMethod.GET)
    public String showScriptSubmissions(final ModelMap model) {
        model.addAttribute("scripts", scriptsService.findAllLockedScripts());
        return "scripts/show-submissions";
    }
}
