package hu.otpmobil.ticket.controller;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.ticket.service.EventService;
import hu.otpmobil.common.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/getEvent/{eventId}")
    public EventDetailsDto getEvent(@PathVariable Long eventId) {
        return eventService.getEvent(eventId);
    }
}
