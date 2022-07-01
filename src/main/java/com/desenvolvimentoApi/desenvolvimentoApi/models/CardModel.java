package com.desenvolvimentoApi.desenvolvimentoApi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class CardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "holder", length = 60)
    private String holder;

    @Column(name = "number", length = 16)
    private String number;

    @Column(name = "cvv", length = 3)
    private Integer cvv;

    @Column(name = "month_expire", length = 2)
    private Integer month_expire;

    @Column(name = "year_expire", length = 2)
    private Integer year_expire;

    public CardModel(){
    }

    public CardModel(String name, String number, Integer cvv, Integer month_expire, Integer year_expire) {
        this.holder = name;
        this.number = number;
        this.cvv = cvv;
        this.month_expire = month_expire;
        this.year_expire = year_expire;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Integer getMonth_expire() {
        return month_expire;
    }

    public void setMonth_expire(Integer month_expire) {
        this.month_expire = month_expire;
    }

    public Integer getYear_expire() {
        return year_expire;
    }

    public void setYear_expire(Integer year_expire) {
        this.year_expire = year_expire;
    }
}
