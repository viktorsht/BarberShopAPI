package com.api.barber.app.schedule.services;

import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    final private ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleEntity> listSchedule(){
        return scheduleRepository.findAll();
    }

    public ScheduleEntity createSchedule(ScheduleDTO scheduleDTO){
        var entity = new ScheduleEntity(
                scheduleDTO.scheduledTime(),
                scheduleDTO.service(),
                scheduleDTO.payment(),
                scheduleDTO.client(),
                scheduleDTO.barber()
        );
        return scheduleRepository.save(entity);
    }
}
