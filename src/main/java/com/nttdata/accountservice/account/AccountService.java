package com.nttdata.accountservice.account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface AccountService {

    Mono<Void> openAccount( Mono<AccountDTO> requestDTO );

    Mono<Void> openAccount( Mono<AccountDTO> requestDTO, String nmDocument );

    Flux<AccountDTO> checkBalance( String idCustomer );
}
