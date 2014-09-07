package edu.hm.cs.fs.scriptinat0r7.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final String method = handlerMethod.getMethod().getName();
            modelAndView.getModelMap().put("method", method);
            final String controller = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
            modelAndView.getModelMap().put("controller", controller);
        }
    }

    private boolean isRedirect(final ModelAndView modelAndView) {
        return modelAndView.getViewName() != null && modelAndView.getViewName().startsWith("redirect:");
    }

}
