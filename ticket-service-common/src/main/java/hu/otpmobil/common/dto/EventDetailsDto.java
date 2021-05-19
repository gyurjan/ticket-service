package hu.otpmobil.common.dto;

import java.util.List;

public class EventDetailsDto {

    private Long eventId;
    private List<SeatDto> seats;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<SeatDto> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDto> seats) {
        this.seats = seats;
    }
}
