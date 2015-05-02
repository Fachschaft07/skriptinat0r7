package edu.hm.cs.fs.scriptinat0r7.controller.admin;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.controller.AbstractController;
import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.model.enums.Role;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentService;

/**
 * Controller to render pages for dealing with {@code Script}s.
 */
@Controller
@RequestMapping("/script-documents")
@Secured("ROLE_FACHSCHAFTLER")
public class ScriptDocumentsController extends AbstractController {

    @Autowired
    private ScriptDocumentService documentsService;

    @RequestMapping
    public String list(final ModelMap model) {
        model.addAttribute("documents", documentsService.findAll());
        return "script-documents/list";
    }

    @RequestMapping(value = "download/{id}", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public void download(@PathVariable("id") final ScriptDocument document, final HttpServletResponse response) throws IOException, UnauthorizedException {
        if (!getCurrentUser().getRole().equals(Role.FACHSCHAFTLER) && !document.isPublic()) {
            throw new UnauthorizedException();
        }

        final byte[] data = documentsService.loadScriptContent(document);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getFilename() + "\"");
        response.setContentType("application/pdf");
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.flushBuffer();
    }

    @RequestMapping(value = "unlock", method = RequestMethod.POST)
    public String unlock(@RequestParam(value = "script[]", required = false) final String[] scriptHashes,
            @RequestParam("action") final String action,
            final RedirectAttributes redirectAttributes) {
        if (ArrayUtils.isEmpty(scriptHashes)) {
            addErrorFlash("Keine Datei ausgewählt!", redirectAttributes);
        } else {
            for (final String scriptHash : scriptHashes) {
                final ScriptDocument script = documentsService.findOne(Long.valueOf(scriptHash));
                script.setReviewState(convertStringToReviewState(action));
                documentsService.save(script);
            }
        }
        return redirect("script-documents");
    }

    private ReviewState convertStringToReviewState(final String str) {
        switch(str) {
        case "accept":
            return ReviewState.FACHSCHAFTLERAPPROVED;
        case "decline":
            return ReviewState.DELETED;
            default:
                throw new IllegalArgumentException("unknown review state");
        }
    }

}
