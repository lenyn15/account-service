package com.nttdata.accountservice.lib.account_transaction;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Repository( "accountTransactionRepository" )
public interface AccountTransactionRepository extends ReactiveMongoRepository<AccountTransaction, String> {
}
