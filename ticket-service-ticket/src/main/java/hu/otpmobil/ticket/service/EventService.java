package hu.otpmobil.ticket.service;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.common.security.TechnicalUser;
import hu.otpmobil.ticket.client.PartnerClient;
import hu.otpmobil.common.dto.EventDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    private String token;

    private final Logger logger =  LoggerFactory.getLogger(EventService.class);

    @Autowired
    private PartnerClient partnerClient;

    @PostConstruct
    public void loginToPartner() {
        ResponseEntity<Object> login = partnerClient.login(new TechnicalUser("ticket", "ticket"));
        this.token = Objects.requireNonNull(login.getHeaders().get("authorization")).get(0);
    }

    public List<EventDto> getEvents() {
        logger.info("GetEvents request received.");
        return partnerClient.getEvents(token);
    }

    public EventDetailsDto getEvent(Long eventId) {
        logger.info("GetEvent request received: {}", eventId);
        return partnerClient.getEvent(token, eventId);
    }

    public ReservationResult reserve(Long eventId, String seatId) {
        logger.info("Reservation request received: eventId: {0}, seatId: {1}", eventId);
        return partnerClient.reserve(token, eventId, seatId);
    }

}
