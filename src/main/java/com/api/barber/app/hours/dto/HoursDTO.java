package com.api.barber.app.hours.dto;

import java.time.LocalTime;
public record HoursDTO(LocalTime time, Boolean status ) {
}
