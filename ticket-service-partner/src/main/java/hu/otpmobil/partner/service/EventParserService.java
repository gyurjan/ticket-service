package hu.otpmobil.partner.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.otpmobil.partner.dao.EventDataHolder;
import hu.otpmobil.partner.dto.EventDetailsListDto;
import hu.otpmobil.partner.dto.EventListDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@Service
public class EventParserService {

    @Value("classpath:getEvents.json")
    private Resource eventsResource;

    @Value("classpath:getEvent1.json")
    private Resource getEvent1Resource;

    @Value("classpath:getEvent2.json")
    private Resource getEvent2Resource;

    @Value("classpath:getEvent3.json")
    private Resource getEvent3Resource;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private EventDataHolder eventDataHolder;

    @PostConstruct
    public void loadEvents() throws IOException {
        EventListDto eventListDto = objectMapper.readValue(eventsResource.getInputStream(), EventListDto.class);
        eventDataHolder.getEvents().addAll(eventListDto.getData());

        loadEventDetails(getEvent1Resource);
        loadEventDetails(getEvent2Resource);
        loadEventDetails(getEvent3Resource);
    }

    private void loadEventDetails(Resource resource) throws IOException {
        EventDetailsListDto eventDetailsListDto = objectMapper.readValue(resource.getInputStream(), EventDetailsListDto.class);
        eventDataHolder.getEventDetailsDtoMap().put(eventDetailsListDto.getData().getEventId(), eventDetailsListDto.getData());
    }
}
