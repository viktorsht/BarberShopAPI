package com.api.barber.app.schedule.error;

public class NotFoundScheduleException extends RuntimeException {

    public NotFoundScheduleException(String message) {
        super(message);
    }
}
