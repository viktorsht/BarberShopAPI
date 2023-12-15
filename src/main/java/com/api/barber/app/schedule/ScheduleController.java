package com.api.barber.app.schedule;

import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.repository.ScheduleRepository;
import com.api.barber.app.schedule.services.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedule")
public class ScheduleController {
    final private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<ScheduleEntity> getSchedule(){
        return scheduleService.listSchedule();
    }

    @PostMapping
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try {
            ScheduleEntity entity = scheduleService.createSchedule(scheduleDTO);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Erro ao criar agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
