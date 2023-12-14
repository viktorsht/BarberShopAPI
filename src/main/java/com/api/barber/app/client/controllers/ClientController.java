package com.api.barber.app.client.controllers;

import com.api.barber.app.client.dto.ClientDTO;
import com.api.barber.app.client.entity.ClientEntity;
import com.api.barber.app.client.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
