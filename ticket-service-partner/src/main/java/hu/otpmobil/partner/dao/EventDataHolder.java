package hu.otpmobil.partner.dao;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventDataHolder {

    private final List<EventDto> events = new ArrayList<>();
    private final Map<Long, EventDetailsDto> eventDetailsDtoMap = new HashMap<>();

    public List<EventDto> getEvents() {
        return events;
    }

    public Map<Long, EventDetailsDto> getEventDetailsDtoMap() {
        return eventDetailsDtoMap;
    }

}
