package hu.otpmobil.partner.controller;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.partner.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public List<EventDto> getEvents() {
        return eventService.getEventDtos();
    }

    @GetMapping("/getEvent/{eventId}")
    public EventDetailsDto getEvent(@PathVariable Long eventId) {
        return eventService.getEvent(eventId);
    }

    @PostMapping("/reserve")
    public ReservationResult reserve(Long eventId, String seatId) {
        return eventService.reserve(eventId, seatId);
    }
}
