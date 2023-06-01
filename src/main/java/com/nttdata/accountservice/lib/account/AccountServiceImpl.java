package com.nttdata.accountservice.lib.account;

import com.nttdata.accountservice.client.customer.CustomerClient;
import com.nttdata.accountservice.enums.AccountType;
import com.nttdata.accountservice.exception.RequestException;
import com.nttdata.accountservice.mapper.AccountToDTO;
import com.nttdata.accountservice.mapper.AccountToEntity;
import com.nttdata.accountservice.util.Constants;
import com.nttdata.accountservice.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Service( "accountService" )
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Qualifier( "accountRepository" )
    private final AccountRepository accountRepository;

    @Qualifier( "customerClient" )
    private final CustomerClient customerClient;

    /**
     *
     */
    @Override
    public Mono<Void> openAccount( Mono<AccountDTO> requestDTO ) {
        return requestDTO.flatMap( accountDTO -> {
            accountDTO.setNmAccount( Util.generateNumAccount() );
            accountDTO.setAvailableBalance( BigDecimal.ZERO );

            if ( Objects.isNull( accountDTO.getCustomerDTO() ) ) {
                throw new RequestException( "Debe ingresar los datos del cliente" );
            }

            return this.customerClient.addNewCustomer( Mono.just( accountDTO.getCustomerDTO() ) )
                    .flatMap( response -> {
                        Map<?, ?> data = Objects.requireNonNull( response.getBody() ).getData();
                        Account account = AccountToEntity.INSTANCE.apply( accountDTO );
                        account.setCustomerId( ( String ) data.get( Constants.Key.INFO ) );
                        return this.accountRepository.save( account ).then();
                    } );
        } );
    }

    /**
     *
     */
    @Override
    public Mono<Void> openAccount( Mono<AccountDTO> requestDTO, String nmDocument ) {
        return this.customerClient.getCustomerInformation( nmDocument )
                .map( response -> {
                    Map<?, ?> data = Objects.requireNonNull( response.getBody() ).getData();
                    LinkedHashMap<?, ?> infoData = ( LinkedHashMap<?, ?> ) data.get( Constants.Key.INFO );
                    return infoData.get( "idCustomer" ).toString();
                } ).flatMap( idCustomer -> requestDTO.flatMap( accountDTO -> {
                    accountDTO.setNmAccount( Util.generateNumAccount() );
                    accountDTO.setAvailableBalance( BigDecimal.ZERO );

                    if ( accountDTO.getType() < 1 || accountDTO.getType() > 3 ) {
                        throw new RequestException( "El tipo de cuenta permitido está entre 1 y 3" );
                    }

                    AccountType accountType = Util.getAccountTypes().get( accountDTO.getType() );
                    return this.accountRepository.findByCustomerIdAndAccountType( idCustomer, accountType )
                            .hasElement()
                            .flatMap( existAccount -> {
                                if ( Boolean.TRUE.equals( existAccount ) ) {
                                    String dsAccountType = accountType.label;
                                    throw new RequestException( "El cliente ya tiene una cuenta " + dsAccountType );
                                }

                                Account account = AccountToEntity.INSTANCE.apply( accountDTO );
                                account.setCustomerId( idCustomer );
                                return this.accountRepository.save( account ).then();
                            } );
                } ) );
    }

    /**
     *
     */
    @Override
    public Flux<List<AccountDTO>> checkBalance( String customerId ) {
        return this.accountRepository.findByCustomerId( customerId )
                .map( AccountToDTO.INSTANCE )
                .collectList()
                .flux();
    }
}
