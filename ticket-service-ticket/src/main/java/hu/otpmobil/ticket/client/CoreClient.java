package hu.otpmobil.ticket.client;

import hu.otpmobil.common.dto.PaymentRequest;
import hu.otpmobil.common.dto.PaymentResult;
import hu.otpmobil.ticket.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core", configuration = FeignConfiguration.class, url = "localhost:8081")
public interface CoreClient {

    @GetMapping("/pay")
    PaymentResult pay(@RequestBody PaymentRequest paymentRequest);

}
