package com.nttdata.accountservice.lib.account_transaction;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Repository( "accountTransactionRepository" )
public interface AccountTransactionRepository extends ReactiveMongoRepository<AccountTransaction, String> {

    Flux<AccountTransaction> findByFcTransactionBetween( LocalDateTime startMonth, LocalDateTime endMonth );

    Flux<AccountTransaction> findByCustomerId( String customerId );
}
