package com.api.barber.app.hoursActive.dto;

import java.time.LocalTime;
public record HoursActiveDTO(LocalTime time, Boolean status ) {
}
