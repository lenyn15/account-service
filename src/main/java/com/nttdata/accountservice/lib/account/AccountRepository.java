package com.nttdata.accountservice.lib.account;

import com.nttdata.accountservice.enums.AccountType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Repository( "accountRepository" )
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Flux<Account> findByCustomerId( String customerId );

    Mono<Account> findByCustomerIdAndAccountType( String customerId, AccountType accountType );
}
