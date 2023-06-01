package com.nttdata.accountservice.rest.account;

import com.nttdata.accountservice.lib.account.AccountDTO;
import com.nttdata.accountservice.util.Response;
import com.nttdata.accountservice.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@RestController
@RequestMapping( "/account" )
@RequiredArgsConstructor
@Tag( name = "account", description = "the account API" )
public class AccountRest {

    @Qualifier( "accountResource" )
    private final AccountResource accountResource;


    @Operation( operationId = "openNewAccount",
            summary = "Open an account when is a new customer",
            tags = { "account" },
            responses = {
                    @ApiResponse( responseCode = "201", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @PostMapping
    public Mono<ResponseEntity<Response>> openNewAccount(
            @Parameter( name = "AccountDTO", required = true ) @Valid @RequestBody Mono<AccountDTO> requestDTO ) {
        return this.accountResource.openAccount( requestDTO ).map( ResponseUtil::getRestResponse );
    }

    @Operation( operationId = "openAccount",
            summary = "Open an account to existing customer",
            tags = { "account" },
            responses = {
                    @ApiResponse( responseCode = "201", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @PostMapping( "{nmDocument}" )
    public Mono<ResponseEntity<Response>> openAccount(
            @Parameter( name = "nmDocument", description = "Customer's document number", required = true ) @PathVariable String nmDocument,
            @Parameter( name = "AccountDTO", required = true ) @Valid @RequestBody Mono<AccountDTO> requestDTO ) {
        return this.accountResource.openAccount( requestDTO, nmDocument ).map( ResponseUtil::getRestResponse );
    }

    @Operation( operationId = "checkBalance",
            summary = "Check all accounts of the customer",
            tags = { "account" },
            responses = {
                    @ApiResponse( responseCode = "200", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) ),
                    @ApiResponse( responseCode = "400", description = "Invalid ID supplied", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) ),
                    @ApiResponse( responseCode = "404", description = "Accounts not found", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @GetMapping( "{customerId}" )
    public Mono<ResponseEntity<Response>> checkBalance(
            @Parameter( name = "customerId", description = "Customer's identifier", required = true ) @PathVariable String customerId ) {
        return this.accountResource.checkBalance( customerId ).map( ResponseUtil::getRestResponse );
    }
}
