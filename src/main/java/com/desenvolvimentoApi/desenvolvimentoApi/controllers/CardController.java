package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.CardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/card")
public class CardController {

    private CardRepository repository;

    public CardController(CardRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/new")
    public ResponseEntity createCard(@RequestBody CardModel card) {
        CardModel response = repository.save(card);

        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(card);
    }

    @GetMapping("/all")
    public ResponseEntity getCards() {
        List<CardModel> cardList = repository.findAll();

        if (cardList != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cardList);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe cartão cadastrado");
    }

    @GetMapping("/all/{id}")
    public ResponseEntity getCard(@PathVariable("id") Long id) {
        Optional<CardModel> response = repository.findById(id);

        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID não encontrado");
    }
}