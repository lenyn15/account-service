package com.nttdata.accountservice.client.feign;

import com.nttdata.accountservice.client.model.CustomerDTO;
import com.nttdata.accountservice.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@FeignClient( name = "customer-service", url = "${client.customer.url}" )
public interface CustomerClient {

    @GetMapping( "{nmDocument}" )
    Mono<ResponseEntity<Response>> getCustomerInformation( @PathVariable String nmDocument );

    @PostMapping
    Mono<ResponseEntity<Response>> addNewCustomer( @Valid @RequestBody Mono<CustomerDTO> requestDTO );
}
