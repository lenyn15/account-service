package com.nttdata.accountservice.rest.account_transaction;

import com.nttdata.accountservice.lib.account_transaction.AccountTransactionDTO;
import com.nttdata.accountservice.util.Response;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface AccountTransactionResource {

    Mono<Response> makeTransaction( Mono<AccountTransactionDTO> requestDTO );

    Mono<Response> checkTransactions( String customerId );
}
