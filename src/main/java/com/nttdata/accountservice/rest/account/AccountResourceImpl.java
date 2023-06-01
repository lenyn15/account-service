package com.nttdata.accountservice.rest.account;

import com.nttdata.accountservice.lib.account.AccountDTO;
import com.nttdata.accountservice.lib.account.AccountService;
import com.nttdata.accountservice.util.ErrorUtil;
import com.nttdata.accountservice.util.Response;
import com.nttdata.accountservice.util.ResponseUtil;
import com.nttdata.accountservice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Component( "accountResource" )
@RequiredArgsConstructor
public class AccountResourceImpl implements AccountResource {

    @Qualifier( "accountService" )
    private final AccountService accountService;

    @Override
    public Mono<Response> openAccount( Mono<AccountDTO> requestDTO ) {
        return this.accountService.openAccount( requestDTO )
                .then( Mono.fromCallable( () -> {
                    Map<String, String> data = Util.getMessageSuccess( "Se creó correctamente la cuenta" );
                    return ResponseUtil.getCreatedResponse( data );
                } ) )
                .onErrorResume( throwable -> ErrorUtil.getErrorMessages( throwable )
                        .flatMap( errors -> Mono.just( ResponseUtil.getBadResponse( errors ) ) )
                );
    }

    @Override
    public Mono<Response> openAccount( Mono<AccountDTO> requestDTO, String nmDocument ) {
        return this.accountService.openAccount( requestDTO, nmDocument )
                .then( Mono.fromCallable( () -> {
                    Map<String, String> data = Util.getMessageSuccess( "Se creó correctamente la cuenta" );
                    return ResponseUtil.getCreatedResponse( data );
                } ) )
                .onErrorResume( throwable -> ErrorUtil.getErrorMessages( throwable )
                        .flatMap( errors -> Mono.just( ResponseUtil.getBadResponse( errors ) ) )
                );
    }

    @Override
    public Mono<Response> checkBalance( String customerId ) {
        return Mono.from( this.accountService.checkBalance( customerId ) )
                .map( accounts -> {
                    Map<String, Object> data = Util.getData( accounts );
                    return ResponseUtil.getOkResponse( data );
                } );
    }
}
