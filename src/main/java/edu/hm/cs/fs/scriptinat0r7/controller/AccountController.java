package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for the account management, e.g. settings.
 */
@Controller
@RequestMapping("/account")
public class AccountController extends AbstractController {

    /**
     * Request method for the index page.
     *
     * @param model
     *            the model to render.
     * @return the logical view name.
     */
    @RequestMapping("/settings")
    public String settings(final ModelMap model) {
        return "account/settings";
    }

}
