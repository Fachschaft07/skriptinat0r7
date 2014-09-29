package edu.hm.cs.fs.scriptinat0r7.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Enriches the model with the controller name.
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

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
        if (handler instanceof HandlerMethod && modelAndView != null && !isRedirect(modelAndView)) {

            // FIXME: funktioniert nicht wenn 404 oder 403 auftritt. Ursache bisher nicht ganz klar
            // Es scheint als würden Filter nur bei "erfolgreichen" requests ausgeführt werden.
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                final UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                modelAndView.getModelMap().addAttribute("userName", user.getUsername());
            }

            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final String method = handlerMethod.getMethod().getName();
            modelAndView.getModelMap().addAttribute("method", method);
            final String controller = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
            modelAndView.getModelMap().addAttribute("controller", controller);
        }
    }

    private boolean isRedirect(final ModelAndView modelAndView) {
        return modelAndView.getViewName() != null && modelAndView.getViewName().startsWith("redirect:");
    }

}
