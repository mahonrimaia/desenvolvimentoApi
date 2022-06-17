package com.desenvolvimentoApi.desenvolvimentoApi.repositories;

import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentCardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCardModel, Long> {
}
