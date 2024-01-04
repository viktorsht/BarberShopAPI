package com.api.barber.app.hours.sevices;

import com.api.barber.app.hours.dto.HoursDTO;
import com.api.barber.app.hours.entity.HoursEntity;
import com.api.barber.app.hours.repositoty.HoursRepository;
import com.api.barber.core.CalculateTime;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HoursService {
    final private HoursRepository hoursRepository;

    public HoursService(HoursRepository hoursRepository) {
        this.hoursRepository = hoursRepository;
    }

    public List<HoursEntity> listHours(){
        return hoursRepository.findAll();
    }

    public List<HoursEntity> listHoursActive() {
        List<HoursEntity> allHours = hoursRepository.findAll();
        List<HoursEntity> copyOfHours = new ArrayList<>(allHours);
        for(HoursEntity hours : copyOfHours){
            System.out.println(hours.getTime());
            if(!CalculateTime.isAfterCurrentTime(hours.getTime())){
                allHours.remove(hours);
            }
        }
        return allHours;
    }

    public void updateHoursActive(int hoursActiveId, HoursDTO hoursDTO){
        var hoursEntity = hoursRepository.findById(hoursActiveId);
        if(hoursEntity.isPresent()) {
            var hours = hoursEntity.get();
            if(hoursDTO.status() != null){
                hours.setStatus(hoursDTO.status());
                hoursRepository.save(hours);
            }
        }
    }

}
