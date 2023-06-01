package com.nttdata.accountservice.client.customer;

import com.nttdata.accountservice.util.ErrorUtil;
import com.nttdata.accountservice.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Component( "customerClient" )
@RequiredArgsConstructor
public class CustomerClient {

    @Qualifier( "webClient" )
    private final WebClient webClient;

    @Value( "${client.customer.url}" )
    private String customerUri;

    public Mono<ResponseEntity<Response>> getCustomerInformation( String identifier ) {
        return this.webClient.get()
                .uri( this.customerUri + "/{identifier}", identifier )
                .retrieve()
                .onStatus( HttpStatus::is4xxClientError, ErrorUtil::getErrorsFromApi )
                .toEntity( Response.class );
    }

    public Mono<ResponseEntity<Response>> addNewCustomer( Mono<CustomerDTO> requestDTO ) {
        return this.webClient.post()
                .uri( this.customerUri )
                .body( requestDTO, CustomerDTO.class )
                .retrieve()
                .onStatus( HttpStatus::is4xxClientError, ErrorUtil::getErrorsFromApi )
                .toEntity( Response.class );
    }
}
