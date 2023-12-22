package com.api.barber.app.schedule.error;

public class NotDeleteScheduleException extends RuntimeException {

    public NotDeleteScheduleException(String message) {
        super(message);
    }
}
