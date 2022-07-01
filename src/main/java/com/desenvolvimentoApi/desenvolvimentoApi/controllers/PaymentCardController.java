package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.CardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.ClientModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentCardModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.CardRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.ClientRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.PaymentCardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/payment/card")
public class PaymentCardController {

    private final CardRepository cardRepository;

    private final PaymentCardRepository paymentRepository;

    private final ClientRepository clientRepository;

    public PaymentCardController(CardRepository cardRepository, PaymentCardRepository paymentRepository, ClientRepository clientRepository) {
        this.cardRepository = cardRepository;
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<PaymentCardModel> paymentWithCard(@RequestBody @Valid PaymentCardModel payment) {

        Optional<CardModel> cardFromDatabase = cardRepository.findByNumber(payment.getCard().getNumber());
        Optional<ClientModel> clientFromDatabase = clientRepository.findByCpfAndEmail(payment.getClient().getCpf(), payment.getClient().getEmail());

        ClientModel client = new ClientModel();

        if(clientFromDatabase.isPresent()) {
            client.setId(clientFromDatabase.get().getId());
            client.setName(clientFromDatabase.get().getName());
            client.setCpf(clientFromDatabase.get().getCpf());
            client.setEmail(clientFromDatabase.get().getEmail());
        } else {
            client.setName(payment.getClient().getName());
            client.setCpf(payment.getClient().getCpf());
            client.setEmail(payment.getClient().getEmail());

            client = clientRepository.save(client);
        }

        if (cardFromDatabase.isPresent()) {
            PaymentCardModel paymentWithCard = new PaymentCardModel(
                    client.getId(),
                    payment.getValue(),
                    cardFromDatabase.get().getId()
            );

            paymentRepository.save(paymentWithCard);

            paymentWithCard.setCard(payment.getCard());

            return ResponseEntity.status(HttpStatus.CREATED).body(paymentWithCard);
        } else {
            CardModel card = new CardModel(
                    payment.getCard().getHolder(),
                    payment.getCard().getNumber(),
                    payment.getCard().getCvv(),
                    payment.getCard().getMonth_expire(),
                    payment.getCard().getYear_expire()
            );

            CardModel cardSaved = cardRepository.save(card);

            PaymentCardModel paymentWithCard = new PaymentCardModel(
                    client.getId(),
                    payment.getValue(),
                    cardSaved.getId()
            );

            paymentRepository.save(paymentWithCard);

            paymentWithCard.setCard(payment.getCard());

            return ResponseEntity.status(HttpStatus.CREATED).body(paymentWithCard);
        }

    }
}