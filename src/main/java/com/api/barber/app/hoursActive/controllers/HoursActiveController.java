package com.api.barber.app.hoursActive.controllers;

import com.api.barber.app.hoursActive.dto.HoursActiveDTO;
import com.api.barber.app.hoursActive.entity.HoursActiveEntity;
import com.api.barber.app.hoursActive.sevices.HoursActiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hoursActive")
public class HoursActiveController {
    final private HoursActiveService hoursActiveService;

    public HoursActiveController(HoursActiveService hoursActiveService) {
        this.hoursActiveService = hoursActiveService;
    }

    @GetMapping
    public List<HoursActiveEntity> getHoursActive(){
        return hoursActiveService.listHoursActive();
    }

    @GetMapping("/{dayId}")
    public List<HoursActiveEntity> getHoursActiveByDay(@PathVariable("dayId") int dayId){
        return hoursActiveService.listHoursDay(dayId);
    }

    @PutMapping("/{hoursActiveId}")
    public ResponseEntity<Void> putHoursActive(@PathVariable("hoursActiveId") int hoursActiveId, @RequestBody HoursActiveDTO hoursActiveDTO){
        hoursActiveService.updateHoursActive(hoursActiveId, hoursActiveDTO);
        return ResponseEntity.noContent().build();
    }


}
