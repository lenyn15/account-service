package com.nttdata.accountservice.lib.account_transaction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface AccountTransactionService {

    Mono<Void> makeTransaction( Mono<AccountTransactionDTO> requestDTO );

    Flux<List<AccountTransactionDTO>> checkTransactions( String idCustomer );
}
