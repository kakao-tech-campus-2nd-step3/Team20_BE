package com.gamsa.activity.exception;

public class ActivityAlreadyExistsException extends RuntimeException {

    public ActivityAlreadyExistsException(String message) {
        super(message);
    }
}
