package com.api.barber.app.daysActives.controllers;

import com.api.barber.app.daysActives.dto.DaysActivesDTO;
import com.api.barber.app.daysActives.entity.DaysEntity;
import com.api.barber.app.daysActives.services.DaysActivesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/daysActives")
public class DaysActivesController {
    private final DaysActivesService daysActivesService;

    public DaysActivesController(DaysActivesService daysActivesService) {
        this.daysActivesService = daysActivesService;
    }

    @GetMapping
    public List<DaysEntity> getDays(){
        return daysActivesService.listForDaysActives();
    }

    @GetMapping("/{dayId}")
    public ResponseEntity<DaysEntity> isActiveDays(@PathVariable("dayId") int dayId){
        var day = daysActivesService.getDaysActivesById(dayId);
        if(day.isPresent()){
            return ResponseEntity.ok(day.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{dayId}")
    public ResponseEntity<Void> updateDaysServices(@PathVariable("dayId") int dayId, @RequestBody DaysActivesDTO daysActivesDTO){
        daysActivesService.updateDay(dayId, daysActivesDTO);
        return ResponseEntity.noContent().build();
    }
}
