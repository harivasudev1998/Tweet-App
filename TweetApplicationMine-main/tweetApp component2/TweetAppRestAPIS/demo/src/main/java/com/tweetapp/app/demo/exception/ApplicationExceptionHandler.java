package com.tweetapp.app.demo.exception;

import com.tweetapp.app.demo.domain.ErrorMessage;
import com.tweetapp.app.demo.utility.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UserAlreadyExists.class})
    public ResponseEntity userAlreadyExists(UserAlreadyExists userAlreadyExists) {
        return new ResponseEntity(new ErrorMessage(Constants.ERROR, HttpStatus.BAD_REQUEST.value(), userAlreadyExists.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserDoesNotExists.class})
    public ResponseEntity userDoesNotExists(UserDoesNotExists userDoesNotExists) {
        return new ResponseEntity(new ErrorMessage(Constants.ERROR, HttpStatus.BAD_REQUEST.value(), userDoesNotExists.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({DBOPerationException.class})
    public ResponseEntity dBOPerationException(DBOPerationException dBOPerationException) {
        return new ResponseEntity(new ErrorMessage(Constants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), dBOPerationException.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({InvalidDetailsException.class})
    public ResponseEntity invalidDetailsException(InvalidDetailsException invalidDetailsException) {
        return new ResponseEntity(new ErrorMessage(Constants.ERROR, HttpStatus.BAD_REQUEST.value(), invalidDetailsException.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
