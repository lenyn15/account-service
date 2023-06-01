package com.nttdata.accountservice.lib.account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface AccountService {

    Mono<Void> openAccount( Mono<AccountDTO> requestDTO );

    Mono<Void> openAccount( Mono<AccountDTO> requestDTO, String nmDocument );

    Flux<List<AccountDTO>> checkBalance( String idCustomer );
}
