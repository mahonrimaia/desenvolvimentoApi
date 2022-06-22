package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.BoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.BoletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boleto")
public class BoletController {
    private BoletRepository repository;

    public BoletController(BoletRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/new")
    public ResponseEntity createNewBoleto(@RequestBody @Validated BoletModel boleto){
        Long response = repository.save(boleto).getId();
        if (response != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(boleto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verifique os dados informado");
    }

    @GetMapping("/all")
    public ResponseEntity showBoletoList(){
        List<BoletModel> response = repository.findAll();
        if (response != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe boleto cadastrado");
    }
    @GetMapping("/{id}")
    public ResponseEntity showBoletoId(@PathVariable("id") long id){
        Optional<BoletModel> boleto = repository.findById(id);
        if (boleto.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(boleto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado");
    }
}
