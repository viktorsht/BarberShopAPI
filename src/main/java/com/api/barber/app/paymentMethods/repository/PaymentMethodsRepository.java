package com.api.barber.app.paymentMethods.repository;

import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethodsEntity, Integer> {
}
