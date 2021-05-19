package hu.otpmobile.ticket.api.controller;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobile.ticket.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getEvents")
    public List<EventDto> getEvents(@RequestHeader("User-Token") String userToken) {
        return eventService.getEvents(userToken);
    }

    @GetMapping("/getEvent/{eventId}")
    public EventDetailsDto getEvent(@RequestHeader("user-token") String userToken, @PathVariable Long eventId) {
        return eventService.getEvent(userToken, eventId);
    }

}
