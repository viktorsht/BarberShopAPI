package com.api.barber.app.daysActives.controllers;

import com.api.barber.app.daysActives.models.DaysActivesModel;
import com.api.barber.app.daysActives.services.DaysActivesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/days")
public class DaysActivesController {
    private final DaysActivesService daysService;

    public DaysActivesController(DaysActivesService daysService) {
        this.daysService = daysService;
    }

    @GetMapping
    public List<DaysActivesModel> getDays(){
        return daysService.listForDaysActives();
    }
}
