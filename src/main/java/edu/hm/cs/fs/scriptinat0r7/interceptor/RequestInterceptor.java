package edu.hm.cs.fs.scriptinat0r7.interceptor;

import edu.hm.cs.fs.scriptinat0r7.model.enums.ReviewState;
import edu.hm.cs.fs.scriptinat0r7.service.ScriptDocumentService;
import edu.hm.cs.fs.scriptinat0r7.service.StudentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Enriches the model with the controller name.
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StudentOrderService studentOrderService;

    @Autowired
    private ScriptDocumentService scriptDocumentService;

    /**
     * Get controller class name and enrich the model with it.
     *
     * @param request      The request object.
     * @param response     The response to send.
     * @param handler      The handler, e.g. the controller.
     * @param modelAndView the model and view object.
     */
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
                           final Object handler, final ModelAndView modelAndView) {
        if ((handler instanceof HandlerMethod) && (modelAndView != null) && !isRedirect(modelAndView)) {
            enrichModelWithUser(modelAndView.getModelMap());
            enrichModelWithHandler(handler, modelAndView.getModelMap());
            enrichModelWithOrderCount(modelAndView.getModelMap());
            enrichModelWithUnreviewedScriptCount(modelAndView.getModelMap());
        }
    }

    private void enrichModelWithUnreviewedScriptCount(final ModelMap modelMap) {
        final int count = scriptDocumentService.findByReviewState(ReviewState.LOCKED).size();
        modelMap.addAttribute("GLOBAL_scriptsToReviewCount", count);
    }

    private void enrichModelWithOrderCount(final ModelMap modelMap) {
        final int count = studentOrderService.findOrdersNotTransmittedToCopyshop().size();
        modelMap.addAttribute("GLOBAL_ordersNotTransmittedToCopyShopCount", count);
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
     *
     * @param model the model map.
     */
    public static void enrichModelWithUser(final ModelMap model) {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null
                && context.getAuthentication() != null
                && context.getAuthentication().getPrincipal() != null) {
            final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                final UserDetails user = (UserDetails) principal;
                model.addAttribute("userName", user.getUsername());
            }
        }
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        return true;
    }

    private boolean isRedirect(final ModelAndView modelAndView) {
        return (modelAndView.getViewName() != null) && modelAndView.getViewName().startsWith("redirect:");
    }

}
