package hu.otpmobile.ticket.api.client;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobile.ticket.api.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ticket-service", configuration = FeignConfiguration.class, url = "localhost:8083")
public interface TicketClient {

    @GetMapping("/getEvents")
    List<EventDto> getEvents();

    @GetMapping("/getEvent/{eventId}")
    EventDetailsDto getEvent(@PathVariable Long eventId);

    @PostMapping("/pay")
    ReservationResult pay(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam String seatId,
            @RequestParam String cardId);
}
