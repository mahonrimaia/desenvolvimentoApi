package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.ClientModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/client")
public class ClientController {
    private ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping ("/customer/all")
   public ResponseEntity showClientList(){
       List<ClientModel> response = repository.findAll();
       if (response != null){
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe cleinte cadastrado");
    }
    @GetMapping("/customer/{id}")
    public ResponseEntity showClienteId(@PathVariable("id") long id){
        Optional<ClientModel> customer = repository.findById(id);
        if (customer.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado");
    }
    @PostMapping ("/new")
    public ResponseEntity createNewClient(@RequestBody ClientModel client){
        Long response = repository.save(client).getId();
        if (response != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifique os dados informado");
    }

}