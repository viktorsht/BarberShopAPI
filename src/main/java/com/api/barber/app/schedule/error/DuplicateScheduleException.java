package com.api.barber.app.schedule.error;

public class DuplicateScheduleException extends RuntimeException {

    public DuplicateScheduleException(String message) {
        super(message);
    }
}
