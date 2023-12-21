package com.api.barber.app.schedule.controllers;

import com.api.barber.app.barberman.entity.BarberEntity;
import com.api.barber.app.barberman.services.BarberService;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.client.service.ClientService;
import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import com.api.barber.app.paymentMethods.services.PaymentMethodsService;
import com.api.barber.app.schedule.dto.ScheduleDTO;
import com.api.barber.app.schedule.entity.ScheduleEntity;
import com.api.barber.app.schedule.entity.ScheduleResponseEntity;
import com.api.barber.app.schedule.error.DuplicateScheduleException;
import com.api.barber.app.schedule.repository.ScheduleRepository;
import com.api.barber.app.schedule.services.ScheduleService;
import com.api.barber.app.servicesActives.entity.ServicesActiveEntity;
import com.api.barber.app.servicesActives.services.ServicesActivesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {
    final private ScheduleService scheduleService;
    final private ServicesActivesService servicesActivesService;
    final private ClientService clientService;
    final private BarberService barberService;

    final private PaymentMethodsService paymentMethodsService;

    public ScheduleController(ScheduleService scheduleService, ServicesActivesService servicesActivesService, ClientService clientService, BarberService barberService, PaymentMethodsService paymentMethodsService) {
        this.scheduleService = scheduleService;
        this.servicesActivesService = servicesActivesService;
        this.clientService = clientService;
        this.barberService = barberService;
        this.paymentMethodsService = paymentMethodsService;
    }

    /*
    * Optional<ServicesActiveEntity> service = servicesActivesService.listServiceById(entity.getService());
            Optional<PaymentMethodsEntity> paymentMethods = paymentMethodsService.listById(entity.getPaymentMethod());
            Optional<ClientEntity> client = clientService.listClientById(entity.getClient());
            Optional<BarberEntity> barber = barberService.listBarberById(entity.getBarber());

            ScheduleResponseEntity responseEntity = new ScheduleResponseEntity(
                    entity.getId(),
                    entity.getScheduledDay(),
                    service.get(),
                    paymentMethods.get(),
                    client.get(),
                    barber.get(),
                    entity.getScheduledTime()
            );
    *
    * */

    @GetMapping
    public List<ScheduleResponseEntity> getSchedule(){
        List<ScheduleEntity> entity = scheduleService.listSchedule();
        List<ScheduleResponseEntity> entityList = new ArrayList<>();
        for(int i = 0; i < entity.size(); i++) {
            Optional<ServicesActiveEntity> service = servicesActivesService.listServiceById(entity.get(i).getService());
            Optional<PaymentMethodsEntity> paymentMethods = paymentMethodsService.listById(entity.get(i).getPaymentMethod());
            Optional<ClientEntity> client = clientService.listClientById(entity.get(i).getClient());
            Optional<BarberEntity> barber = barberService.listBarberById(entity.get(i).getBarber());
            ScheduleResponseEntity responseEntity = new ScheduleResponseEntity(
                    entity.get(i).getId(),
                    entity.get(i).getScheduledDay(),
                    service.get(),
                    paymentMethods.get(),
                    client.get(),
                    barber.get(),
                    entity.get(i).getScheduledTime()
            );
            entityList.add(responseEntity);
        }
        return entityList;
    }

    @PostMapping
    public ResponseEntity<?> postSchedule(@RequestBody ScheduleDTO scheduleDTO){
        try {
            ScheduleEntity entity = scheduleService.createSchedule(scheduleDTO);
            return new ResponseEntity<>(entity, HttpStatus.CREATED);
        }
        catch (DuplicateScheduleException e) {
            return new ResponseEntity<>("Erro ao criar agendamento: " + e.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception e){
            return new ResponseEntity<>("Erro ao criar agendamento: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*@GetMapping("/{id}")
    public Optional<ScheduleEntity> getSchedule(@PathVariable("id") int id){
        //return scheduleService.updateSchedule(id);
    }*/
}
