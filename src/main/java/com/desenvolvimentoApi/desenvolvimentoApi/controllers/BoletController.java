package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.BoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.BoletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletController {
    private BoletRepository repositoryBolet;


    public BoletController(BoletRepository repository) {
        this.repositoryBolet = repository;
    }

    @GetMapping("/all")
    public ResponseEntity showBoletoList(){
        List<BoletModel> response = repositoryBolet.findAll();
        if (response != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe boleto cadastrado");
    }
    @GetMapping("/{id}")
    public ResponseEntity showBoletoId(@PathVariable("id") long id){
        Optional<BoletModel> boleto = repositoryBolet.findById(id);
        if (boleto.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(boleto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado");
    }
}
