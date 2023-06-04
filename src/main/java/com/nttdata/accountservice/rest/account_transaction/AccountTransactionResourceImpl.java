package com.nttdata.accountservice.rest.account_transaction;

import com.nttdata.accountservice.lib.account_transaction.AccountTransactionDTO;
import com.nttdata.accountservice.lib.account_transaction.AccountTransactionService;
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
@Component( "accountTransactionResource" )
@RequiredArgsConstructor
public class AccountTransactionResourceImpl implements AccountTransactionResource {

    @Qualifier( "accountTransactionService" )
    private final AccountTransactionService accountTransactionService;

    @Override
    public Mono<Response> makeTransaction( Mono<AccountTransactionDTO> requestDTO ) {
        return this.accountTransactionService.makeTransaction( requestDTO )
                .then( Mono.fromCallable( () -> {
                    Map<String, String> data = Util.getMessageSuccess( "Se realizó correctamente la transacción" );
                    return ResponseUtil.getCreatedResponse( data );
                } ) )
                .onErrorResume( throwable -> ErrorUtil.getErrorMessages( throwable )
                        .flatMap( errors -> Mono.just( ResponseUtil.getBadResponse( errors ) ) )
                );
    }

    @Override
    public Mono<Response> checkTransactions( String customerId ) {
        return Mono.from( this.accountTransactionService.checkTransactions( customerId ) )
                .map( transactions -> {
                    Map<String, Object> data = Util.getData( transactions );
                    return ResponseUtil.getOkResponse( data );
                } );
    }
}
