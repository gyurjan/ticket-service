package hu.otpmobil.partner.service;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.common.dto.SeatDto;
import hu.otpmobil.common.error.Errors;
import hu.otpmobil.partner.dao.EventDataHolder;
import hu.otpmobil.partner.exception.ReservationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    @Autowired
    private EventDataHolder eventDataHolder;

    public ReservationResult reserve(Long eventId, String seatId) {
        ReservationResult reservationResult = new ReservationResult();
        EventDetailsDto eventDetailsDto = eventDataHolder.getEventDetailsDtoMap().get(eventId);

        if (Objects.isNull(eventDetailsDto)) {
            throw new ReservationException(Errors.EVENT_NOT_FOUND);
        }

        SeatDto seat = eventDetailsDto.getSeats()
                .stream()
                .filter(seatDto -> seatDto.getId().equals(seatId))
                .findFirst().orElseThrow(() -> new ReservationException(Errors.SEAT_NOT_FOUND));

        if (seat.getReserved()) {
            throw new ReservationException(Errors.SEAT_ALREADY_RESERVED);
        }

        Long reservationId = System.currentTimeMillis();

        reservationResult.setSuccess(true);
        reservationResult.setReservationId(reservationId);
        return reservationResult;
    }

    public List<EventDto> getEventDtos() {
        return eventDataHolder.getEvents();
    }

    public EventDetailsDto getEvent(Long eventId) {
        return eventDataHolder.getEventDetailsDtoMap().get(eventId);
    }
}
