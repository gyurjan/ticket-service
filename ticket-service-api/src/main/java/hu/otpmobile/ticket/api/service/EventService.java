package hu.otpmobile.ticket.api.service;

import hu.otpmobil.common.dto.EventDetailsDto;
import hu.otpmobil.common.dto.EventDto;
import hu.otpmobil.common.dto.UserValidationResponseDto;
import hu.otpmobil.common.error.Errors;
import hu.otpmobile.ticket.api.client.CoreClient;
import hu.otpmobile.ticket.api.client.TicketClient;
import hu.otpmobile.ticket.api.exception.UserValidationException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private CoreClient coreClient;

    @Autowired
    private TicketClient ticketClient;

    public List<EventDto> getEvents(String userToken) {
        logger.info("GetEvents request received with userToken: {}", userToken);
        validateUserToken(userToken);
        return ticketClient.getEvents();
    }

    public EventDetailsDto getEvent(String userToken, Long eventId) {
        logger.info("GetEvent request received with userToken: {}", userToken);
        validateUserToken(userToken);
        return ticketClient.getEvent(eventId);
    }

    private void validateUserToken(String userToken) {
        if (StringUtils.isEmpty(userToken)) {
            throw new UserValidationException(Errors.TOKEN_NOT_FOUND);
        }

        UserValidationResponseDto userValidationResponseDto = coreClient.validateToken(userToken);
        if (!userValidationResponseDto.getSuccess()) {
            throw new UserValidationException(Errors.TOKEN_EXPIRED);
        }
    }

}
