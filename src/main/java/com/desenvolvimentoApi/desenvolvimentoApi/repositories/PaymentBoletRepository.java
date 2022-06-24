package com.desenvolvimentoApi.desenvolvimentoApi.repositories;

import com.desenvolvimentoApi.desenvolvimentoApi.models.PaymentBoletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentBoletRepository extends JpaRepository<PaymentBoletModel,Long> {
}
