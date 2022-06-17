package com.desenvolvimentoApi.desenvolvimentoApi.models;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "client")
public class ClientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Campo Obrigatório")
    @Column(name = "name", length = 60, nullable = false)
    private String name;

    @NotBlank(message = "Campo Obrigatório")
    @Email(message = "E-mail informado invalido")
    @Column(name = "email", length = 60, nullable = false)
    private String email;

    @NotBlank(message = "Campo Obrigatório")
    @CPF(message = "Numero do CPF informado inválido ")
    @Column(name = "cpf", length = 11, nullable = false)
    private String cpf;

    public ClientModel() {
    }

    public ClientModel(Long id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ClientModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }

}