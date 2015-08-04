package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller responsible for the login page and logout.
 */
@Controller
@RequestMapping("/")
public class LoginLogoutController extends AbstractController {
    @RequestMapping("logout")
    public void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.logout();
        response.sendRedirect("login?logout");
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
