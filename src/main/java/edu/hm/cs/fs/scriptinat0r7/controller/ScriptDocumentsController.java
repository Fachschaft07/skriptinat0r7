package edu.hm.cs.fs.scriptinat0r7.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.service.LectureService;
import edu.hm.cs.fs.scriptinat0r7.service.ProfessorService;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentService;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptService;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/script-documents")
@Secured("ROLE_FACHSCHAFTLER")
public class ScriptDocumentsController extends AbstractController {

    @Autowired
    private ScriptService scriptsService;

    @Autowired
    private LectureService lecturesService;

    @Autowired
    private ScriptDocumentService documentsService;

    @Autowired
    private ProfessorService professorService;

    @RequestMapping
    public String list(final ModelMap model) {
        model.addAttribute("documents", documentsService.findAll());
        return "script-documents/list";
    }

    @RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    public void download(@PathVariable("id") final ScriptDocument document, final HttpServletResponse response) throws IOException {
        final byte[] data = documentsService.loadScriptContent(document);
        response.setContentType("application/pdf");
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.flushBuffer();
    }

    @RequestMapping(value = "unlock", method = RequestMethod.POST)
    public String unlock(@RequestParam("script[]") final String[] scriptHashes, @RequestParam("action") final String action) {
        // TODO
        System.out.println("1");
        System.out.println(action);
        for (final String str : scriptHashes) {
            System.out.println(str);
        }
        return redirect("script-documents");
    }

}
