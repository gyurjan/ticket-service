package hu.otpmobil.ticket.service;

import hu.otpmobil.common.dto.*;
import hu.otpmobil.common.error.Errors;
import hu.otpmobil.ticket.client.CoreClient;
import hu.otpmobil.ticket.exception.PaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.Objects;

@Service
public class PaymentService {

    private final Logger logger =  LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private CoreClient coreClient;

    @Autowired
    private EventService eventService;

    @Autowired
    private Clock clock;

    public ReservationResult pay(Long userId, Long eventId, String seatId, String carId) {
        logger.info("Payment request received: userId: {0},  eventId: {1}, seatId: {2}, cardId: {3}", userId, eventId, seatId, carId);
        ReservationResult reservationResult = new ReservationResult();
        long currentTimeStamp = clock.millis();

        EventDto eventDto = eventService.getEvents().stream()
                .filter(event -> event.getEventId().equals(eventId))
                .findFirst().orElseThrow(
                        () -> new PaymentException(Errors.EVENT_NOT_FOUND)
                );

        if (eventDto.getStartTimeStamp() * 1000 < currentTimeStamp) {
            throw new PaymentException(Errors.START_TIME_IN_THE_PAST);
        }

        EventDetailsDto eventDetailsDto = eventService.getEvent(eventId);
        if (Objects.isNull(eventDetailsDto)) {
            throw new PaymentException(Errors.EVENT_NOT_FOUND);
        }

        SeatDto seat = eventDetailsDto.getSeats().stream()
                .filter(seatDto -> seatDto.getId().equals(seatId))
                .findFirst().orElseThrow(() -> new PaymentException(Errors.SEAT_NOT_FOUND));

        if (seat.getReserved()) {
            throw new PaymentException(Errors.SEAT_ALREADY_RESERVED);
        }

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setUserId(userId);
        paymentRequest.setAmount(seat.getPrice());
        paymentRequest.setCardId(carId);
        PaymentResult paymentResult = coreClient.pay(paymentRequest);

        if (paymentResult.isSuccess()) {
            reservationResult = eventService.reserve(eventId, seatId);
        } else {
            reservationResult.setSuccess(false);
            reservationResult.setErrorCode(paymentResult.getErrorCode());
        }

        return reservationResult;
    }

}
