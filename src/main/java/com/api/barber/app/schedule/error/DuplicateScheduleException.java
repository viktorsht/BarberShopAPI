package com.api.barber.domain.error;

public class DuplicateScheduleException extends RuntimeException {

    public DuplicateScheduleException(String message) {
        super(message);
    }
}
