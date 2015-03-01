package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;
import edu.hm.cs.fs.scriptinat0r7.interceptor.RequestInterceptor;

/**
 * Special controller, used by spring to serve error pages.
 */
@ControllerAdvice
public class ExceptionController extends AbstractController {
    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingServletRequestParameterException.class, ServletRequestBindingException.class, TypeMismatchException.class, HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ModelAndView handle400() {
        return buildModelAndView("errors/400");
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class, AccessDeniedException.class})
    public ModelAndView handleUnauthorized() {
        return buildModelAndView("errors/403");
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    public ModelAndView handleNoSuchRequestHandlingMethodException() {
        return buildModelAndView("errors/404");
    }

    private ModelAndView buildModelAndView(final String errorPage) {
        final ModelAndView modelAndView = new ModelAndView(errorPage);
        RequestInterceptor.enrichModelWithUser(modelAndView.getModelMap());
        return modelAndView;
    }
}
