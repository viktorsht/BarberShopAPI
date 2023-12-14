package com.api.barber.app.barberman.controllers;

import com.api.barber.app.barberman.dto.BarberDTO;
import com.api.barber.app.barberman.entity.BarberEntity;
import com.api.barber.app.barberman.services.BarberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
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

    @GetMapping("/{barberId}")
    public ResponseEntity<BarberEntity> getBarber(@PathVariable("barberId") int barberId){
        var barber = barberService.listBarberById(barberId);
        if(barber.isPresent()){
            return ResponseEntity.ok(barber.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> postBarber(@RequestBody BarberDTO barberDTO){
        try {
            var barber = barberService.createBarber(barberDTO);
            return ResponseEntity.created(URI.create("/api/v1/barbers" + barber.getId())).build();
        }
        catch (Exception e){
            String error = "Erro na criação: " + e.getMessage();
            return ResponseEntity.badRequest().body(error);
        }
    }

    //@DeleteMapping()


}
