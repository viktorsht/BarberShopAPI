package com.api.barber.app.paymentMethods.controllers;

import com.api.barber.app.paymentMethods.dto.PaymentMethodsDTO;
import com.api.barber.app.paymentMethods.entity.PaymentMethodsEntity;
import com.api.barber.app.paymentMethods.services.PaymentMethodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentMethods")
public class PaymentMethodsController {
    final private PaymentMethodsService paymentMethodsService;

    public PaymentMethodsController(PaymentMethodsService paymentMethodsService) {
        this.paymentMethodsService = paymentMethodsService;
    }

    @GetMapping
    public List<PaymentMethodsEntity> getPaymentMethods(){
        return paymentMethodsService.listPaymentMethods();
    }

    @PutMapping("/{paymentMethodsId}")
    public ResponseEntity<Void> putPaymentMethods(@PathVariable("paymentMethodsId") int paymentMethodsId, @RequestBody PaymentMethodsDTO paymentMethodsDTO){
        paymentMethodsService.updatePaymentMethods(paymentMethodsId, paymentMethodsDTO);
        return ResponseEntity.noContent().build();
    }
}
