package com.nttdata.accountservice.mapper;

import com.nttdata.accountservice.lib.account_transaction.AccountTransaction;
import com.nttdata.accountservice.lib.account_transaction.AccountTransactionDTO;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountTransactionToDTO implements Function<AccountTransaction, AccountTransactionDTO> {

    INSTANCE;

    @Override
    public AccountTransactionDTO apply( AccountTransaction entity ) {
        AccountTransactionDTO dto = new AccountTransactionDTO();

        if ( Objects.nonNull( entity ) ) {
            BeanUtils.copyProperties( entity, dto );

            if ( Objects.nonNull( entity.getTransactionType() ) ) {
                dto.setTransactionType( entity.getTransactionType().name() );
            }
        }

        return dto;
    }
}
