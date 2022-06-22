package com.desenvolvimentoApi.desenvolvimentoApi.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "payment_boleto")
public class PaymentBoletModel {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Column(name = "client_id")
    private long client_id;
    @NotNull
    @Column(name= "value")
    private double value;
    @Column(name = "bolet_id")
    private long bolet_id;

    public PaymentBoletModel(){

    }

    public PaymentBoletModel(long client_id, double value, long bolet_id) {
        this.client_id = client_id;
        this.value = value;
        this.bolet_id = bolet_id;
    }

    public long getId() {
        return id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getBolet_id() {
        return bolet_id;
    }

    public void setBolet_id(long bolet_id) {
        this.bolet_id = bolet_id;
    }
}
