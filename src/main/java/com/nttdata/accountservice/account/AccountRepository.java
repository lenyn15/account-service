package com.nttdata.accountservice.account;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Repository( "accountRepository" )
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Flux<Account> findByCustomerId( String customerId );
}