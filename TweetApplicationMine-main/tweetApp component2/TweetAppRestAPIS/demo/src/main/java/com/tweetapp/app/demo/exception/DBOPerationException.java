package com.tweetapp.app.demo.exception;


import com.tweetapp.app.demo.utility.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = Constants.DBERROR)
public class DBOPerationException extends RuntimeException {
    public DBOPerationException(String message) {
        super(message);
    }
}
