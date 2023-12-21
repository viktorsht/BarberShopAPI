package com.api.barber.app.schedule.services;

import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.error.DuplicateScheduleException;
import com.api.barber.app.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        if(!scheduleRepository.existsByScheduledDateTimeString(scheduleDTO.scheduledTime())) {
            var entity = new ScheduleEntity(
                    scheduleDTO.scheduledTime(),
                    scheduleDTO.service(),
                    scheduleDTO.payment(),
                    scheduleDTO.client(),
                    scheduleDTO.barber(),
                    LocalDateTime.now()
            );
            return scheduleRepository.save(entity);
        }
        else{
            throw new DuplicateScheduleException("Erro ao criar agendamento: Já existe um agendamento para o horário especificado.");
        }
    }

    public /*Optional<ScheduleEntity>*/ void updateSchedule(int scheduleDTO){
        //var id = scheduleRepository.existsByScheduleClient(scheduleDTO);
        //return scheduleRepository.findById(id);
    }
    public void deleteSchedule(){}
}
