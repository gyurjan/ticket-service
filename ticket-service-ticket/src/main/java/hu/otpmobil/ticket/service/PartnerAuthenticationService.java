package hu.otpmobil.ticket.service;

import hu.otpmobil.common.error.Errors;
import hu.otpmobil.common.security.TechnicalUser;
import hu.otpmobil.ticket.client.PartnerClient;
import hu.otpmobil.ticket.exception.PartnerConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class PartnerAuthenticationService {

    private Logger logger = LoggerFactory.getLogger(PartnerAuthenticationService.class);

    private String token = null;

    @Autowired
    private PartnerClient partnerClient;

    public String getAuthToken() {
        if (StringUtils.isEmpty(token)) {
            try {
                ResponseEntity<Object> login = partnerClient.login(new TechnicalUser("ticket", "ticket"));
                token = Objects.requireNonNull(login.getHeaders().get("authorization")).get(0);
            } catch (Exception e) {
                throw new PartnerConnectionException(Errors.PARTNER_SERVICE_NOT_AVAILABLE);
            }
        }
        return token;
    }
}
