package com.api.barber.app.hours.controllers;

import com.api.barber.app.hours.dto.HoursDTO;
import com.api.barber.app.hours.entity.HoursEntity;
import com.api.barber.app.hours.sevices.HoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hours")
public class HoursController {
    final private HoursService hoursService;

    public HoursController(HoursService hoursService) {
        this.hoursService = hoursService;
    }

    @GetMapping
    public List<HoursEntity> getHoursActive(){
        return hoursService.listHours();
    }

    @PutMapping("/{hoursId}")
    public ResponseEntity<Void> putHoursActive(@PathVariable("hoursId") int hoursId, @RequestBody HoursDTO hoursDTO){
        hoursService.updateHoursActive(hoursId, hoursDTO);
        return ResponseEntity.noContent().build();
    }


}
