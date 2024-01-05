package com.api.barber.app.schedule.services;

import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.entity.ScheduleResponseEntity;
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
        boolean existSchedule = scheduleRepository.existsByScheduledDateTimeStringAndBarberId(scheduleDTO.scheduledTime(), scheduleDTO.barber());
        if(!existSchedule) {
            var entity = new ScheduleEntity(
                    scheduleDTO.scheduledTime(),
                    scheduleDTO.service(),
                    1,
                    //scheduleDTO.payment(), // no app não funciona o pagamento ainda ... só tirar o comentário e apagar o 1
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

    public Optional<ScheduleEntity> myNextSchedule(String user, int userId) {
        if(user.equals("client")){
            return scheduleRepository.findNearestScheduleByClient(userId);
        }
        //return null;
        return scheduleRepository.findNearestScheduleByBarber(userId);
    }


    public List<ScheduleEntity> listScheduleByUser(String user, int userId){
        if(user.equals("client")){
            return scheduleRepository.findScheduleByClient(userId);
        }
        if(user.equals("barber")){
            return scheduleRepository.findScheduleByBarber(userId);
        }
        return scheduleRepository.findAll();
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
