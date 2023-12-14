package com.api.barber.app.servicesActives.controllers;

import com.api.barber.app.servicesActives.dto.ServicesActivesDTO;
import com.api.barber.app.servicesActives.entity.ServicesActiveEntity;
import com.api.barber.app.servicesActives.services.ServicesActivesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/services")
public class ServicesActiveController {
    final private ServicesActivesService servicesActivesService;

    public ServicesActiveController(ServicesActivesService servicesActivesService) {
        this.servicesActivesService = servicesActivesService;
    }

    @GetMapping
    public List<ServicesActiveEntity> getAllServices(){
        return servicesActivesService.listServices();
    }

    @PostMapping
    public ResponseEntity<?> postService(@RequestBody ServicesActivesDTO servicesActivesDTO){
        try {
            var serviceCreate = servicesActivesService.createServices(servicesActivesDTO);
            return ResponseEntity.created(URI.create("/api/v1/services" + serviceCreate.toString())).build();
        }
        catch (Exception e){
            String error = "Erro na criação" + e.getMessage();
            return ResponseEntity.badRequest().body(error);
        }
    }
}
