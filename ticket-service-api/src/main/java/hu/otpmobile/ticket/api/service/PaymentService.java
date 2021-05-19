package hu.otpmobile.ticket.api.service;

import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.common.dto.UserValidationResponseDto;
import hu.otpmobile.ticket.api.client.CoreClient;
import hu.otpmobile.ticket.api.client.TicketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private CoreClient coreClient;

    @Autowired
    private TicketClient ticketClient;

    public ReservationResult pay(String userToken, Long eventId, String seatId, String cardId) {
        logger.info("Payment request received with parameters: userToken {}, eventId {}, seat {}, card {}",
                userToken, eventId, seatId, cardId);
        ReservationResult reservationResult = new ReservationResult();
        UserValidationResponseDto userValidationResponseDto = coreClient.validateToken(userToken);

        if (!userValidationResponseDto.getSuccess()) {
            reservationResult.setSuccess(false);
            reservationResult.setErrorCode(userValidationResponseDto.getErrorCode());
        } else {
            reservationResult = ticketClient.pay(userValidationResponseDto.getUserId(), eventId, seatId, cardId);
        }

        return reservationResult;
    }

}
