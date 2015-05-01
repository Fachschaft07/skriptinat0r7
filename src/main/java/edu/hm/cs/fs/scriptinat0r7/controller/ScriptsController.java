package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;
import edu.hm.cs.fs.scriptinat0r7.model.Lecture;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentService;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptService;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/scripts")
public class ScriptsController extends AbstractController {

    private static final String LECTURE_LIST_VIEW = "scripts/lecture-list";

    @Autowired
    private ScriptService scriptsService;

    @Autowired
    private LectureService lecturesService;

    @Autowired
    private ScriptDocumentService documentsService;

    /**
     * Display lectures and script count.
     *
     * @param model
     *            the model used by the view.
     * @param request
     *            the http request.
     * @return the logical view name.
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showScriptsByLectures(final ModelMap model, final HttpServletRequest request) {
        final List<Lecture> lectures = lecturesService.findLecturesWithPublicScript();

        for (final Lecture lecture : lectures) {
            lecture.setUsedScripts(scriptsService.findPublicByLecture(lecture)); // TODO PRELOAD
        }

        model.addAttribute("lectures", lectures);
        return LECTURE_LIST_VIEW;
    }

    /**
     * Shows a detail page with all scripts for one lecture.
     * @param model the model used by the view.
     * @param id the lecture id.
     * @return the logical view name
     */
    @RequestMapping(value = "lecture/{id}", method = RequestMethod.GET)
    public String showLectureDetail(final ModelMap model, @PathVariable("id") final Lecture lecture) throws UnauthorizedException {
        model.addAttribute("lecture", lecture);

        final List<Script> publicScripts = new ArrayList<>();
        final List<Script> nonPublicScipts = new ArrayList<>();
        for (final Script script : scriptsService.findByLecture(lecture)) {
            script.setScriptDocuments(new HashSet<>(documentsService.findByScript(script)));
            if (script.getScriptDocumentsCount() > 0) {
                if (script.hasPublicDocuments()) {
                    publicScripts.add(script);
                } else {
                    nonPublicScipts.add(script);
                }
            }
        }

        model.addAttribute("publicScripts", publicScripts);
        model.addAttribute("nonPublicScripts", nonPublicScipts);
        return "scripts/lecture-detail";
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String showScriptDetail(final ModelMap model, @PathVariable("id") final Script script) throws UnauthorizedException {
        final List<ScriptDocument> documents = documentsService.findByScript(script);
        model.addAttribute("script", script);
        model.addAttribute("documents", documents);
        model.addAttribute("hasPublicScripts", documents.stream().anyMatch(ScriptDocument::isPublic));
        return "scripts/detail";
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

    @Secured("ROLE_FACHSCHAFTLER")
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(final ModelMap model, @PathVariable("id") final Script script) {
        script.setLectures(new HashSet<>(lecturesService.findByScript(script)));
        model.addAttribute("script", script);
        model.addAttribute("lectures", lecturesService.findAll());
        return "scripts/edit";
    }
}
