package com.desenvolvimentoApi.desenvolvimentoApi.controllers;

import com.desenvolvimentoApi.desenvolvimentoApi.models.BoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.ClientModel;
import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentBoletModel;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.BoletRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.ClientRepository;
import com.desenvolvimentoApi.desenvolvimentoApi.repositories.PaymentBoletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment/boleto")
public class PaymentBoletController {
    private PaymentBoletRepository repositoryPayment;
    private BoletRepository repositoryBolet;
    private ClientRepository repositoryClient;

    public PaymentBoletController(PaymentBoletRepository repositoryPayment, BoletRepository repositoryBolet, ClientRepository repositoryClient) {
        this.repositoryPayment = repositoryPayment;

        this.repositoryBolet = repositoryBolet;

        this.repositoryClient = repositoryClient;
    }

    @PostMapping("/new")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity paymentBolet(@RequestBody @Valid PaymentBoletModel payment) {
        Optional<ClientModel> customer = repositoryClient.findByCpfAndEmail(payment.getClient().getCpf(),payment.getClient().getEmail());
       ClientModel client = new ClientModel();
        if (customer.isPresent()){
            BoletModel bolet = new BoletModel("09876556532377625888", "12/07/2022");
            BoletModel boletSaved = repositoryBolet.save(bolet);
            PaymentBoletModel payment_bolet = new PaymentBoletModel(
                    customer.get().getId(),
                    payment.getValue(),
                    boletSaved.getId()
            );
            PaymentBoletModel payment_bolet_saved = repositoryPayment.save(payment_bolet);
            return ResponseEntity.status(HttpStatus.OK).body(payment_bolet_saved);
        } else {
            client.setName(payment.getClient().getName());
            client.setCpf(payment.getClient().getCpf());
            client.setEmail(payment.getClient().getEmail());

            client = repositoryClient.save(client);

            BoletModel bolet = new BoletModel("09876556532377625777", "10/07/2022");
            BoletModel boletSaved = repositoryBolet.save(bolet);
            PaymentBoletModel payment_bolet = new PaymentBoletModel(
                    client.getId(),
                    payment.getValue(),
                    boletSaved.getId()
            );
            PaymentBoletModel payment_bolet_saved = repositoryPayment.save(payment_bolet);
        }
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @GetMapping ("/all")
    public ResponseEntity showPaymentList(){
        List<PaymentBoletModel> response = repositoryPayment.findAll();
        if (response != null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe boleto cadastrado");
    }
    @GetMapping("/{id}")
    public ResponseEntity showPaymentId(@PathVariable("id") long id){
        Optional<PaymentBoletModel> customer = repositoryPayment.findById(id);
        if (customer.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id não encontrado");
    }

}
