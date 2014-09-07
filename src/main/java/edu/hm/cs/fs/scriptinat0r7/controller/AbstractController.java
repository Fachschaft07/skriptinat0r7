package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Base class for controller instances.
 */
public class AbstractController {

    /**
     * Produces a valid logical view name which will be used as redirect by spring.
     * @param url The url to redirect to.
     * @return A logical view name.
     */
    protected String redirect(final String url) {
        return String.format("redirect:/%s", url);
    }

    /**
     * Adds a new flash message, which provides the user with success feedback.
     * @param message The message to display.
     * @param redirectAttributes A RedirectAttributes instance, usually injected in to the controller.
     */
    protected void addSuccessFlash(final String message, final RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", message);
    }

}
