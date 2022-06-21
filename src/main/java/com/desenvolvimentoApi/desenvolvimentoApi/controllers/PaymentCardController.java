package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.CardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentCardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.CardRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.PaymentCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/payment/card")
public class PaymentCardController {

    private CardRepository cardRepository;

    private PaymentCardRepository paymentRepository;

    public PaymentCardController(CardRepository cardRepository, PaymentCardRepository paymentRepository) {
        this.cardRepository = cardRepository;
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    public ResponseEntity paymentWithCard(@RequestBody @Valid PaymentCardModel payment) {

        Optional<CardModel> cardFromDatabase = cardRepository.findByNumber(payment.getCard().getNumber());

        if (cardFromDatabase.isPresent()) {
            PaymentCardModel paymentWithCard = new PaymentCardModel(
                    payment.getClient_id(),
                    payment.getValue(),
                    cardFromDatabase.get().getId()
            );

            paymentRepository.save(paymentWithCard);

            paymentWithCard.setCard(payment.getCard());

            return ResponseEntity.status(HttpStatus.CREATED).body(paymentWithCard);
        } else {
            CardModel card = new CardModel(
                    payment.getCard().getName(),
                    payment.getCard().getNumber(),
                    payment.getCard().getCvv(),
                    payment.getCard().getMonth_expire(),
                    payment.getCard().getYear_expire()
            );

            CardModel cardSaved = cardRepository.save(card);

            PaymentCardModel paymentWithCard = new PaymentCardModel(
                    payment.getClient_id(),
                    payment.getValue(),
                    cardSaved.getId()
            );

            paymentRepository.save(paymentWithCard);

            paymentWithCard.setCard(payment.getCard());

            return ResponseEntity.status(HttpStatus.CREATED).body(paymentWithCard);
        }
    }
}