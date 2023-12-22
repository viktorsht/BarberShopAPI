package com.api.barber.app.schedule.services;

import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.error.DuplicateScheduleException;
import com.api.barber.app.schedule.error.NotDeleteScheduleException;
import com.api.barber.app.schedule.repository.ScheduleRepository;
import java.time.temporal.ChronoUnit;

import com.api.barber.core.CalculateTime;
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
                    LocalDateTime.now() // create
            );
            return scheduleRepository.save(entity);
        }
        else{
            throw new DuplicateScheduleException("Erro ao criar agendamento: Já existe um agendamento para o horário especificado.");
        }
    }

    public void updateSchedule(int scheduleId, ScheduleDTO scheduleDTO){
        var scheduleEntity = scheduleRepository.findById(scheduleId);
        if(scheduleEntity.isPresent()) {
            var schedule = scheduleEntity.get();
            boolean isUpdate = CalculateTime.isTimeDifferenceGreaterThan2Hours(schedule.getScheduledDay());
            if(isUpdate == true){
                if(scheduleDTO.scheduledTime() != null){
                    schedule.setScheduledDay(scheduleDTO.scheduledTime());
                    scheduleRepository.save(schedule);
                }
            }
            else{
                throw new NotDeleteScheduleException("Atualização negada!");
            }
        }
        else{
            throw new NotDeleteScheduleException("Agendamento não encontrado");
        }
    }
    public void deleteSchedule(int scheduleId){
        var scheduleEntity = scheduleRepository.findById(scheduleId);
        if(scheduleEntity.isPresent()) {
            var schedule = scheduleEntity.get();
            boolean isUpdate = CalculateTime.isTimeDifferenceGreaterThan2Hours(schedule.getScheduledDay());
            if(isUpdate == true){
                scheduleRepository.deleteById(scheduleId);
            }
            else{
                throw new NotDeleteScheduleException("Exclusão negada!");
            }
        }
        else{
            throw new NotDeleteScheduleException("Agendamento não encontrado");
        }
    }
}
