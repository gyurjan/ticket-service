package hu.otpmobile.ticket.api.controller;

import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobile.ticket.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ReservationResult pay(@RequestHeader("User-Token") String userToken,
                                 @RequestParam Long eventId,
                                 @RequestParam String seatId,
                                 @RequestParam String cardId) {
        return paymentService.pay(userToken, eventId, seatId, cardId);
    }

}
