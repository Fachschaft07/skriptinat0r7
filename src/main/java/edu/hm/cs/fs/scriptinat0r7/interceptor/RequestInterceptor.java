package edu.hm.cs.fs.scriptinat0r7.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Enriches the model with the controller name.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = Logger.getLogger(RequestInterceptor.class);

    /**
     * Get controller class name and enrich the model with it.
     * @param request The request object.
     * @param response The response to send.
     * @param handler The handler, e.g. the controller.
     * @param modelAndView the model and view object.
     */
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
            final Object handler, final ModelAndView modelAndView) {
        if ((handler instanceof HandlerMethod) && (modelAndView != null) && !isRedirect(modelAndView)) {
            enrichModelWithUser(modelAndView.getModelMap());
            enrichModelWithHandler(handler, modelAndView.getModelMap());
        }
    }

    private void enrichModelWithHandler(final Object handler, final ModelMap model) {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final String method = handlerMethod.getMethod().getName();
        model.addAttribute("method", method);
        final String controller = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
        model.addAttribute("controller", controller);
    }

    /**
     * Adds the current user to the model map.
     * @param model the model map.
     */
    public static void enrichModelWithUser(final ModelMap model) {
        final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            final UserDetails user = (UserDetails)principal;
            model.addAttribute("userName", user.getUsername());
        }
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        return true;
    }

    private boolean isRedirect(final ModelAndView modelAndView) {
        return (modelAndView.getViewName() != null) && modelAndView.getViewName().startsWith("redirect:");
    }

}
