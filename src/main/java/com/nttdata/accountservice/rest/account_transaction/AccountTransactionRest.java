package com.nttdata.accountservice.rest.account_transaction;

import com.nttdata.accountservice.lib.account_transaction.AccountTransactionDTO;
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
@RequestMapping( "/account-transaction" )
@RequiredArgsConstructor
@Tag( name = "account-transaction", description = "the account-transaction API" )
public class AccountTransactionRest {

    @Qualifier( "accountTransactionResource" )
    private final AccountTransactionResource accountTransactionResource;

    @Operation( operationId = "makeTransaction",
            summary = "Allow to make a transaction like deposit or withdraw",
            tags = { "account-transaction" },
            responses = {
                    @ApiResponse( responseCode = "201", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @PostMapping
    public Mono<ResponseEntity<Response>> makeTransaction(
            @Parameter( name = "AccountTransactionDTO", required = true ) @Valid @RequestBody Mono<AccountTransactionDTO> requestDTO ) {
        return this.accountTransactionResource.makeTransaction( requestDTO ).map( ResponseUtil::getRestResponse );
    }

    @Operation( operationId = "checkTransactions",
            summary = "Get all transactions made by the customer",
            tags = { "account-transaction" },
            responses = {
                    @ApiResponse( responseCode = "200", description = "Successful operation", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) ),
                    @ApiResponse( responseCode = "400", description = "Invalid ID supplied", content = @Content( mediaType = "application/json", schema = @Schema( implementation = Response.class ) ) )
            } )
    @GetMapping( "{customerId}" )
    public Mono<ResponseEntity<Response>> checkTransactions(
            @Parameter( name = "customerId", description = "Customer's identifier", required = true ) @PathVariable String customerId ) {
        return this.accountTransactionResource.checkTransactions( customerId ).map( ResponseUtil::getRestResponse );
    }
}
