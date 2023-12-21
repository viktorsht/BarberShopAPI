package com.api.barber.app.paymentMethods.services;

import com.api.barber.app.paymentMethods.dto.PaymentMethodsDTO;
import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import com.api.barber.app.paymentMethods.repository.PaymentMethodsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodsService {
    final private PaymentMethodsRepository paymentMethodsRepository;

    public PaymentMethodsService(PaymentMethodsRepository paymentMethodsRepository) {
        this.paymentMethodsRepository = paymentMethodsRepository;
    }

    public List<PaymentMethodsEntity> listPaymentMethods(){
        return paymentMethodsRepository.findAll();
    }

    public Optional<PaymentMethodsEntity> listById(int paymentId){
        return paymentMethodsRepository.findById(paymentId);
    }

    public void updatePaymentMethods(int paymentMethodsId, PaymentMethodsDTO paymentMethodsDTO){
        var paymentMethodsEntity = paymentMethodsRepository.findById(paymentMethodsId);
        if(paymentMethodsEntity.isPresent()){
            var payment = paymentMethodsEntity.get();
            if(paymentMethodsDTO.name() != null){
                payment.setName(paymentMethodsDTO.name());
            }
            if(paymentMethodsDTO.status() != null){
                payment.setStatus(paymentMethodsDTO.status());
            }
            paymentMethodsRepository.save(payment);
        }
    }
}
