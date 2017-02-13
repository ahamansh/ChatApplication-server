package com.chatapp.exception;

import static java.lang.String.format;

public class FailedToRegisterException extends RuntimeException {
    public FailedToRegisterException(String username) {
        super(format("Failed to login with username %s", username));
    }
}
