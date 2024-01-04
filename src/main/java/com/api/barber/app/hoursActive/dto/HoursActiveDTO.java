package com.api.barber.app.hoursActive.dto;

import java.time.LocalTime;
public record HoursActiveDTO(int day, LocalTime time, Boolean status ) {
}
