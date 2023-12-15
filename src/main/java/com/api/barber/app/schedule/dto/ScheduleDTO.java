package com.api.barber.app.schedule.dto;

public record ScheduleDTO(String scheduledTime ,int service, int payment, int client, int barber) {
}
