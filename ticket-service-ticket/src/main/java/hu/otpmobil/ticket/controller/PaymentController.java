package hu.otpmobil.ticket.controller;

import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.ticket.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ReservationResult pay(@RequestParam Long userId,
                                 @RequestParam Long eventId,
                                 @RequestParam String seatId,
                                 @RequestParam String cardId) {
        return paymentService.pay(userId, eventId, seatId, cardId);
    }

}
