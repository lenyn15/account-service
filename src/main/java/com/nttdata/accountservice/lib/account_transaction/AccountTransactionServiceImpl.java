package com.nttdata.accountservice.lib.account_transaction;

import com.nttdata.accountservice.enums.TransactionType;
import com.nttdata.accountservice.exception.RequestException;
import com.nttdata.accountservice.lib.account.Account;
import com.nttdata.accountservice.lib.account.AccountRepository;
import com.nttdata.accountservice.mapper.AccountTransactionToDTO;
import com.nttdata.accountservice.mapper.AccountTransactionToEntity;
import com.nttdata.accountservice.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service( "accountTransactionService" )
@RequiredArgsConstructor
public class AccountTransactionServiceImpl implements AccountTransactionService {

    @Qualifier( "accountRepository" )
    private final AccountRepository accountRepository;

    @Qualifier( "accountTransactionRepository" )
    private final AccountTransactionRepository accountTransactionRepository;

    /**
     *
     */
    @Override
    public Mono<Void> makeTransaction( Mono<AccountTransactionDTO> requestDTO ) {
        return requestDTO.flatMap( accountTransactionDTO -> {
            BigDecimal transactionAmount = accountTransactionDTO.getNmAmount();
            LocalDateTime transactionDate = LocalDateTime.now();

            if ( accountTransactionDTO.getType() < 1 || accountTransactionDTO.getType() > 2 ) {
                throw new RequestException( "El tipo de transacción solo puede ser 1 o 2" );
            }

            Mono<Account> accountMono = this.accountRepository.findById( accountTransactionDTO.getIdAccount() )
                    .switchIfEmpty( Mono.error( new RequestException( "No se encontró la cuenta" ) ) );

            return accountMono.flatMap( account -> {
                BigDecimal availableBalance = account.getAvailableBalance();
                TransactionType transactionType = Util.getTransactionTypes().get( accountTransactionDTO.getType() );

                return this.movementsAreWithinMonthlyLimit( account, transactionDate )
                        .flatMap( isWithinMonthlyLimit -> {
                            this.validateTransaction( transactionAmount, availableBalance, transactionType, isWithinMonthlyLimit );

                            accountTransactionDTO.setFcTransaction( transactionDate );
                            accountTransactionDTO.setNmAmount( transactionAmount );
                            accountTransactionDTO.setNmAccount( account.getNmAccount() );
                            accountTransactionDTO.setCustomerId( account.getCustomerId() );

                            BigDecimal newBalance = TransactionType.WITHDRAWAL.equals( transactionType ) ?
                                    availableBalance.subtract( transactionAmount ) : availableBalance.add( transactionAmount );
                            account.setAvailableBalance( newBalance );

                            return this.accountTransactionRepository.save( AccountTransactionToEntity.INSTANCE.apply( accountTransactionDTO ) )
                                    .then( this.accountRepository.save( account ) )
                                    .then();
                        } );
            } );
        } );
    }

    /**
     *
     */
    @Override
    public Flux<List<AccountTransactionDTO>> checkTransactions( String customerId ) {
        return this.accountTransactionRepository.findByCustomerId( customerId )
                .map( AccountTransactionToDTO.INSTANCE )
                .collectList()
                .flux();
    }

    private Mono<Boolean> movementsAreWithinMonthlyLimit( Account account, LocalDateTime transactionDate ) {
        Integer movementLimit = account.getMovementLimit();
        LocalDateTime startOfMonth = transactionDate.withDayOfMonth( 1 ).withHour( 0 ).withMinute( 0 ).withSecond( 0 );
        LocalDateTime endOfMonth = transactionDate.withDayOfMonth( transactionDate.getMonth()
                .length( transactionDate.toLocalDate().isLeapYear() ) ).withHour( 23 ).withMinute( 59 ).withSecond( 59 );

        return this.accountTransactionRepository.findByFcTransactionBetween( startOfMonth, endOfMonth )
                .collectList()
                .flatMap( Mono::just )
                .map( transactions -> {
                    boolean isWithinMonthlyLimit = Boolean.TRUE;
                    if ( movementLimit > 0 ) {
                        long quantity = transactions.stream()
                                .map( AccountTransaction::getNmAccount )
                                .filter( nmAccount -> account.getNmAccount().equals( nmAccount ) )
                                .count();
                        isWithinMonthlyLimit = quantity < movementLimit;
                    }

                    return isWithinMonthlyLimit;
                } );
    }

    private void validateTransaction( BigDecimal transactionAmount, BigDecimal availableBalance,
                                      TransactionType transactionType, Boolean isWithinMonthlyLimit ) {
        if ( Boolean.FALSE.equals( isWithinMonthlyLimit ) ) {
            throw new RequestException( "Ya alcanzó el limite de movimientos mensuales" );
        }

        if ( TransactionType.WITHDRAWAL.equals( transactionType ) &&
                availableBalance.compareTo( transactionAmount ) < 0 ) {
            throw new RequestException( "No hay saldo disponible" );
        }
    }
}
