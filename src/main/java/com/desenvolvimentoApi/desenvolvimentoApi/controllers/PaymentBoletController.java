package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.BoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentBoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.BoletRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.PaymentBoletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment/bolet")
public class PaymentBoletController {
    private PaymentBoletRepository repositoryPayment;
    private BoletRepository repositoryBolet;
    public PaymentBoletController(PaymentBoletRepository repository, BoletRepository repositoryBolet) {
        this.repositoryPayment = repository;

        this.repositoryBolet = repositoryBolet;
    }

    @PostMapping
    public ResponseEntity paymentBolet(@RequestBody @Valid PaymentBoletModel payment){
        BoletModel bolet = new BoletModel("09876556532377625676","17/06/2021");
        BoletModel boletSaved = repositoryBolet.save(bolet);
        PaymentBoletModel payment_bolet = new PaymentBoletModel(payment.getClient_id(), payment.getValue(),boletSaved.getId());
        PaymentBoletModel payment_bolet_saved = repositoryPayment.save(payment_bolet);
        return ResponseEntity.status(HttpStatus.OK).body(payment_bolet_saved);
    }

}
