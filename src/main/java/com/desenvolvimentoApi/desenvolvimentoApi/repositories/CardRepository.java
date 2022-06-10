package com.desenvolvimentoApi.desenvolvimentoApi.repositories;

import com.desenvolvimentoApi.desenvolvimentoApi.models.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardModel, Long> {
}
