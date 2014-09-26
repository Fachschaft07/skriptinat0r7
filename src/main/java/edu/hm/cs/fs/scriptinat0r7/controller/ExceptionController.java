package edu.hm.cs.fs.scriptinat0r7.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import edu.hm.cs.fs.scriptinat0r7.exception.UnauthorizedException;

/**
 * Special controller, used by spring to serve error pages.
 */
@ControllerAdvice
public class ExceptionController extends AbstractController {

    private static final String ERRORS_400 = "errors/400";

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ServletRequestBindingException.class)
    public String handleServletRequestBindingException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public String handleMissingServletRequestPartException() {
        return ERRORS_400;
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UnauthorizedException.class)
    public String handleUnauthorized() {
        return "errors/403";
    }

    /**
     * Handles an error case.
     * @return an error page
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchRequestHandlingMethodException.class)
    public String handleNoSuchRequestHandlingMethodException() {
        return "errors/404";
    }
}
