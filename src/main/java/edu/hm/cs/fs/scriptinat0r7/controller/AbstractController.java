package edu.hm.cs.fs.scriptinat0r7.controller;

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

}
