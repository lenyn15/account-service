package com.nttdata.accountservice.account;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service( "accountService" )
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Qualifier( "accountRepository" )
    private final AccountRepository accountRepository;

    @Override
    public Mono<Void> openAccount( Mono<AccountDTO> requestDTO ) {
        return null;
    }

    @Override
    public Mono<Void> openAccount( Mono<AccountDTO> requestDTO, String nmDocument ) {
        return null;
    }

    @Override
    public Flux<AccountDTO> checkBalance( String idCustomer ) {
        return null;
    }
}
