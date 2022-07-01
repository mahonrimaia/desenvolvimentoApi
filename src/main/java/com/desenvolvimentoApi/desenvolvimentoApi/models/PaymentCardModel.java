package com.desenvolvimentoApi.desenvolvimentoApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity(name="payment_card")
public class PaymentCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long client_id;

    @NotNull
    private Double value;

    private Long card_id;

    @Transient
    private ClientModel client;

    @Transient
    private CardModel card;

    public PaymentCardModel() {
    }

    public PaymentCardModel(Long client_id, Double value, Long card_id) {
        this.client_id = client_id;
        this.value = value;
        this.card_id = card_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getCard_id() {
        return card_id;
    }

    public void setCard_id(Long card_id) {
        this.card_id = card_id;
    }

    public CardModel getCard() {
        return card;
    }

    public void setCard(CardModel card) {
        this.card = card;
    }

    public ClientModel getClient() {
        return client;
    }

    public void setClient(ClientModel client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "PaymentCardModel{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", value=" + value +
                ", card_id=" + card_id +
                '}';
    }
}
