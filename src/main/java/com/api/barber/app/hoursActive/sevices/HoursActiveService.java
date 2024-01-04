package com.api.barber.app.hoursActive.sevices;

import com.api.barber.app.hoursActive.dto.HoursActiveDTO;
import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import com.api.barber.app.hoursActive.repositoty.HoursActiveRepository;
import org.springframework.stereotype.Service;

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

    public List<HoursActiveEntity> listHoursDay(int day){
        return hoursActiveRepository.findByDay(day);
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
