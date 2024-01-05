package com.api.barber.app.hoursActive.sevices;

import com.api.barber.app.hours.entity.HoursEntity;
import com.api.barber.app.hoursActive.dto.HoursActiveDTO;
import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import com.api.barber.app.hoursActive.repositoty.HoursActiveRepository;
import com.api.barber.core.CalculateTime;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoursActiveService {
    final private HoursActiveRepository hoursActiveRepository;

    public HoursActiveService(HoursActiveRepository hoursActiveRepository) {
        this.hoursActiveRepository = hoursActiveRepository;
    }

    public List<HoursActiveEntity> listHoursActive(){
        return hoursActiveRepository.findAll();
    }
    
    public List<HoursActiveEntity> listHoursDay(int day) {
        List<HoursActiveEntity> housByDay = hoursActiveRepository.findByDay(day);
        if(LocalDate.now().getDayOfWeek().getValue() == day){
            List<HoursActiveEntity> copyOfHours = new ArrayList<>(housByDay);
            for(HoursActiveEntity hours : copyOfHours){
                if(!CalculateTime.isAfterCurrentTime(hours.getTime())){
                    housByDay.remove(hours);
                }
            }
        }
        return housByDay;
    }

    public void updateHoursActive(int hoursActiveId, HoursActiveDTO hoursActiveDTO){
        var hoursEntity = hoursActiveRepository.findById(hoursActiveId);
        if(hoursEntity.isPresent()) {
            var hours = hoursEntity.get();
            if(hoursActiveDTO.status() != null){
                hours.setStatus(hoursActiveDTO.status());
                hoursActiveRepository.save(hours);
            }
        }
    }

}
