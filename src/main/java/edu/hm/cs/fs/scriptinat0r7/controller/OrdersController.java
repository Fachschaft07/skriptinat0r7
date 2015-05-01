package edu.hm.cs.fs.scriptinat0r7.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.exception.PasswordsMissingException;
import edu.hm.cs.fs.scriptinat0r7.model.Script;
import edu.hm.cs.fs.scriptinat0r7.model.ScriptDocument;
import edu.hm.cs.fs.scriptinat0r7.model.StudentOrder;
import edu.hm.cs.fs.scriptinat0r7.service.StudentOrderService;

@Controller
@RequestMapping("/orders")
public class OrdersController extends AbstractController {

    @Autowired
    private StudentOrderService studentOrderService;

    @RequestMapping(method = RequestMethod.POST)
    public String orderScripts(
            @RequestParam(value = "script_document[]") final Collection<ScriptDocument> documentsToOrder,
            @RequestParam(value = "script") final Script script,
            @RequestParam(value = "passwords", required = false, defaultValue = "") final String passwordPost,
            final ModelMap model,
            final RedirectAttributes redirectAttributes) {

        final List<String> passwords = Arrays.asList(StringUtils.split(passwordPost));
        try {
            final StudentOrder order = studentOrderService.placeOrder(documentsToOrder, script, passwords);
            addSuccessFlash("Skripte bestellt! Bestellnummer " + order.getId(), redirectAttributes);
            return redirect("scripts/" + script.getId());
        } catch (final PasswordsMissingException e) {
            // ask for passwords
            model.addAttribute("documentsWithMissingPassword", e.getDocumentsWithMissingPasswords());
            model.addAttribute("documentsWithKnownPassword", e.getDocumentsWithKnownPassword());
            model.addAttribute("script", script);
            model.addAttribute("documents", documentsToOrder);
            return "orders/password";
        } catch (final IllegalArgumentException e) {
            addErrorFlash("Fehler beim Bestellen der Skripte / des Skriptes! " + e.getMessage(), redirectAttributes);
            return redirect("scripts/" + script.getId());
        }
    }
}

