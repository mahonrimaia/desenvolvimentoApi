package com.desenvolvimentoApi.desenvolvimentoApi.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "boleto")
public class BoletModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "line_code", length = 20, nullable = false)
    private String codigoDeBarras ;

    @NotBlank(message = "Campo Obrigatório")
    @DateTimeFormat
    @Column(name = "date_Expired", length = 10, nullable = false)
    private String dateExpired;

    public BoletModel() {
    }

    public BoletModel(String codigoDeBarras, String dateExpired) {
        this.codigoDeBarras = codigoDeBarras;
        this.dateExpired = dateExpired;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getDateExpired() {
        return dateExpired;
    }

    public void setDateExpired(String dateExpired) {
        this.dateExpired = dateExpired;
    }


    @Override
    public String toString() {
        return "BoletModel{" +
                "id=" + id +
                ", codigoDeBarras='" + codigoDeBarras + '\'' +
                ", dateExpired='" + dateExpired + '\'' +
                '}';
    }
}
