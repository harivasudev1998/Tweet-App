package com.tweetapp.app.demo.exception;

import com.tweetapp.app.demo.utility.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Constants.USER_ALREADY_EXISTS)
public class UserAlreadyExists extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(UserAlreadyExists.class);

    public UserAlreadyExists(String message) {
        super(message);
        logger.error(Constants.USER_ALREADY_EXISTS);
    }

}
