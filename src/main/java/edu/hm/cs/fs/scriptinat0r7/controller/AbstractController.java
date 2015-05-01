package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.hm.cs.fs.scriptinat0r7.model.User;

/**
 * Base class for controller instances.
 */
public class AbstractController {

    private static final String ERROR_MESSAGE_KEY = "errorMessage";
    private static final String SUCCESS_MESSAGE_KEY = "successMessage";

    /**
     * Produces a valid logical view name which will be used as redirect by spring.
     * @param url The url to redirect to.
     * @return A logical view name.
     */
    protected String redirect(final String url) {
        return String.format("redirect:/%s", url);
    }

    /**
     * Adds a new flash message, which provides the user with success feedback after a redirect.
     * @param message The message to display.
     * @param redirectAttributes A RedirectAttributes instance, usually injected in to the controller.
     */
    protected void addSuccessFlash(final String message, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(SUCCESS_MESSAGE_KEY, message);
    }

    /**
     * Adds a new flash message, which provides the user with success feedback.
     * @param message The message to display.
     * @param model A ModelMap instance, usually injected in to the controller.
     */
    protected void addSuccessFlash(final String message, final ModelMap model) {
        model.addAttribute(SUCCESS_MESSAGE_KEY, message);
    }

    /**
     * Adds a new flash message, which provides the user with error feedback.
     * @param message The message to display.
     * @param redirectAttributes A RedirectAttributes instance, usually injected in to the controller.
     */
    protected void addErrorFlash(final String message, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(ERROR_MESSAGE_KEY, message);
    }

    /**
     * Adds a new flash message, which provides the user with error feedback after a redirect.
     * @param message The message to display.
     * @param model A ModelMap instance, usually injected in to the controller.
     */
    protected void addErrorFlash(final String message, final ModelMap model) {
        model.addAttribute(ERROR_MESSAGE_KEY, message);
    }

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected User getCurrentUser() {
        return (User) getAuthentication().getPrincipal();
    }

}
