package hu.otpmobil.ticket.client;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.ReservationResult;
import hu.otpmobil.common.security.TechnicalUser;
import hu.otpmobil.ticket.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "partner", configuration = FeignConfiguration.class, url = "localhost:8082")
public interface PartnerClient {

    @PostMapping("/login")
    ResponseEntity<Object> login(@RequestBody TechnicalUser user);

    @GetMapping("/getEvents")
    List<EventDto> getEvents(@RequestHeader("authorization") String token);

    @GetMapping("/getEvent/{eventId}")
    EventDetailsDto getEvent(@RequestHeader("authorization") String token, @PathVariable Long eventId);

    @PostMapping("/reserve")
    ReservationResult reserve(@RequestHeader("authorization") String token,
                              @RequestParam Long eventId,
                              @RequestParam String seatId);

}
