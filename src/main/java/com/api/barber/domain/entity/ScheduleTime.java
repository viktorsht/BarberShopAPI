package com.api.barber.domain.entity;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class ScheduleTime {

    private LocalDateTime dateTime;

    public ScheduleTime(String dateTimeString) {
        this.dateTime = LocalDateTime.parse(dateTimeString);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getTime() {
        return dateTime.toLocalTime().toString();
    }

    public int getDayOfWeek() {
        return dateTime.getDayOfWeek().getValue();
    }
}