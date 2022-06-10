package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.CardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.CardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    private CardRepository repository;

    public CardController(CardRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity createCard(@RequestBody CardModel card) {
        CardModel response = repository.save(card);

        if (response != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(card);
    }
}