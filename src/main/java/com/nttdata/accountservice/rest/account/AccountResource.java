package com.nttdata.accountservice.rest.account;

import com.nttdata.accountservice.lib.account.AccountDTO;
import com.nttdata.accountservice.util.Response;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public interface AccountResource {

    Mono<Response> openAccount( Mono<AccountDTO> requestDTO );

    Mono<Response> openAccount( Mono<AccountDTO> requestDTO, String nmDocument );

    Mono<Response> checkBalance( String customerId );
}
