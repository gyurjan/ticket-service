package hu.otpmobil.ticket.service;

import hu.otpmobil.common.dto.*;
import hu.otpmobil.ticket.client.CoreClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @Mock
    private CoreClient coreClient;

    @Mock
    private EventService eventService;

    @Mock
    private Clock clock;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void testSuccessPayment() {
        PaymentResult paymentResult = new PaymentResult();
        paymentResult.setSuccess(true);

        ReservationResult reservationResult = new ReservationResult();
        reservationResult.setSuccess(true);

        Long userId = 1L;
        Long eventId = 2L;
        String seatId = "1S";
        String cardId = "card1";

        EventDto eventDto = new EventDto();
        eventDto.setEventId(eventId);
        eventDto.setStartTimeStamp(2000L);

        SeatDto seatDto = new SeatDto();
        seatDto.setId(seatId);
        seatDto.setReserved(false);
        seatDto.setPrice(1000);

        EventDetailsDto eventDetailsDto = new EventDetailsDto();
        eventDetailsDto.setEventId(eventId);
        eventDetailsDto.setSeats(Collections.singletonList(seatDto));


        PaymentRequest request = new PaymentRequest();
        request.setCardId(cardId);
        request.setAmount(2000);
        request.setUserId(userId);

        Mockito.when(clock.millis()).thenReturn(1000L);
        Mockito.when(coreClient.pay(Mockito.any())).thenReturn(paymentResult);
        Mockito.when(eventService.getEvent(eventId)).thenReturn(eventDetailsDto);
        Mockito.when(eventService.getEvents()).thenReturn(Collections.singletonList(eventDto));
        Mockito.when(eventService.reserve(eventId, seatId)).thenReturn(reservationResult);

        ReservationResult pay = paymentService.pay(userId, eventId, seatId, cardId);

        Assert.assertTrue(pay.isSuccess());
    }

}
