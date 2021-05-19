package hu.otpmobil.ticket.core.controller;

import hu.otpmobil.common.dto.PaymentRequest;
import hu.otpmobil.common.dto.PaymentResult;
import hu.otpmobil.ticket.core.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public PaymentResult getUserBankCard(@RequestBody PaymentRequest paymentRequest) {
        return paymentService.pay(paymentRequest);
    }

}
