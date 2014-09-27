package edu.hm.cs.fs.scriptinat0r7.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for the login page and logout.
 */
@Controller
@RequestMapping("/")
public class LoginLogoutController extends AbstractController {
    /**
     * Request method for logging the user out.
     *
     * @param request
     *            The request object.
     * @param response
     *            The response object.
     * @throws ServletException
     *             Contract from request.logout();
     * @throws IOException
     *             Contract from response.sendRedirect();
     */
    @RequestMapping("logout")
    public void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.logout();
        response.sendRedirect("login?logout");
    }

    /**
     * Request method for displaying the user login page.
     *
     * @see edu.hm.cs.fs.scriptinat0r7.config.SecurityConfiguration
     *
     * @return The logical view name.
     */
    @RequestMapping("login")
    public String login() {
        return "login";
    }
}
