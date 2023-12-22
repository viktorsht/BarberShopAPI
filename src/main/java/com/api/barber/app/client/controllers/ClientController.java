package com.api.barber.app.client.controllers;

import com.api.barber.app.client.dto.ClientDTO;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController()
@RequestMapping("/api/v1/clients")
public class ClientController {
    final private ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientEntity>> getAllClients(){
        var clients = clientService.listClientsService();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<?> getClientByPhone(@PathVariable("phone") String phone){
        try {
            var client = clientService.searchClientByPhoneNumber(phone);
            return ResponseEntity.ok(client);

        }
        catch (Exception e){
            return new  ResponseEntity<>("Erro ao atualizar agendamento: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postClient(@RequestBody ClientDTO clientDTO){
        try {
            var clientCreated = clientService.createClient(clientDTO);
            return ResponseEntity.created(URI.create("/api/v1/clients" + clientCreated.toString())).build();
        }
        catch (Exception e){
            String error = "Erro na criação" + e.getMessage();
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Void> putClient(@PathVariable("clientId") int clientId, @RequestBody ClientDTO clientDTO){
        clientService.updateClient(clientId, clientDTO);
        return ResponseEntity.noContent().build();
    }


}
