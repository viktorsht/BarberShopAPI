package com.api.barber.app.client.repository;

import com.api.barber.app.client.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByPhone(String phone);
}
