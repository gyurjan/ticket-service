package hu.otpmobile.ticket.api.client;

import hu.otpmobil.common.dto.UserValidationResponseDto;
import hu.otpmobile.ticket.api.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "core-service", configuration = FeignConfiguration.class)
public interface CoreClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/validatetoken")
    UserValidationResponseDto validateToken(@RequestParam("userToken") String token);
}
