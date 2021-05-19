package hu.otpmobil.ticket.service;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.ticket.client.PartnerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private PartnerClient partnerClient;

    @Autowired
    private PartnerAuthenticationService partnerAuthenticationService;

    public List<EventDto> getEvents() {
        logger.info("GetEvents request received.");
        return partnerClient.getEvents(partnerAuthenticationService.getAuthToken());
    }

    public EventDetailsDto getEvent(Long eventId) {
        logger.info("GetEvent request received: {}", eventId);
        return partnerClient.getEvent(partnerAuthenticationService.getAuthToken(), eventId);
    }

    public ReservationResult reserve(Long eventId, String seatId) {
        logger.info("Reservation request received: eventId: {}, seatId: {}", eventId, seatId);
        return partnerClient.reserve(partnerAuthenticationService.getAuthToken(), eventId, seatId);
    }

}
