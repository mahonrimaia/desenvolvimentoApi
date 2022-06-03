package com.desenvolvimentoApi.desenvolvimentoApi.repositories;

import com.desenvolvimentoApi.desenvolvimentoApi.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
