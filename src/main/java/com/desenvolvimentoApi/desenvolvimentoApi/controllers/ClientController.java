package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.ClientModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/client")
public class ClientController {
    private ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/customer")
    public String hello(){
        return "Bem-vindo";
    }

    @PostMapping ("/new")
    public ResponseEntity createNewClient(@RequestBody ClientModel client){
        Long response = repository.save(client).getId();
        if (response != null){
            return ResponseEntity.accepted().body(client);
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }

}
