package com.api.barber.app.paymentMethods.repository;

import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethodsEntity, Integer> {
}
