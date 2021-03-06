package edu.hm.cs.fs.scriptinat0r7.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Indicates that the accessing user is not allowed to view this page.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception {

    private static final long serialVersionUID = -1909247958649340938L;

}
