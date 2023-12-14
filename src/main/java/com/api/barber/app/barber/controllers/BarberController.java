package com.api.barber.app.barber.controllers;

import com.api.barber.app.barber.entity.BarberEntity;
import com.api.barber.app.barber.services.BarberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/barbers")
public class BarberController {

    final private BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @GetMapping
    public List<BarberEntity> listBarbers(){
        return barberService.listBarbersService();
    }
}
