package hu.otpmobil.partner.service;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.common.dto.SeatDto;
import hu.otpmobil.common.error.Errors;
import hu.otpmobil.partner.dao.EventDataHolder;
import hu.otpmobil.partner.exception.ReservationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventDataHolder eventDataHolder;

    public ReservationResult reserve(Long eventId, String seatId) {
        logger.info("Reservation request received: {}, {}", eventId, seatId);
        ReservationResult reservationResult = new ReservationResult();
        EventDetailsDto eventDetailsDto = eventDataHolder.getEventDetailsDtoMap().get(eventId);

        if (Objects.isNull(eventDetailsDto)) {
            logger.error("Event not found with id: {}", eventId);
            throw new ReservationException(Errors.EVENT_NOT_FOUND);
        }

        SeatDto seat = eventDetailsDto.getSeats()
                .stream()
                .filter(seatDto -> seatDto.getId().equals(seatId))
                .findFirst().orElseThrow(() -> new ReservationException(Errors.SEAT_NOT_FOUND));

        if (seat.getReserved()) {
            logger.error("Seat alread reserved: {}", seatId);
            throw new ReservationException(Errors.SEAT_ALREADY_RESERVED);
        }

        Long reservationId = System.currentTimeMillis();

        reservationResult.setSuccess(true);
        reservationResult.setReservationId(reservationId);
        return reservationResult;
    }

    public List<EventDto> getEventDtos() {
        logger.info("Get events request received");
        return eventDataHolder.getEvents();
    }

    public EventDetailsDto getEvent(Long eventId) {
        logger.info("Get event request received with eventId: {}", eventId);
        return eventDataHolder.getEventDetailsDtoMap().get(eventId);
    }
}
