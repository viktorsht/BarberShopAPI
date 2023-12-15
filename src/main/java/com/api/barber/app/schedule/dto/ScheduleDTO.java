package com.api.barber.app.schedule.dto;

public record ScheduleDTO(int day, int hours, int service, int payment, int client, int barber) {
}
