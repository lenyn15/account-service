package com.nttdata.accountservice.mapper;

import com.nttdata.accountservice.enums.TransactionType;
import com.nttdata.accountservice.lib.account_transaction.AccountTransaction;
import com.nttdata.accountservice.lib.account_transaction.AccountTransactionDTO;
import com.nttdata.accountservice.util.Util;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountTransactionToEntity implements Function<AccountTransactionDTO, AccountTransaction> {

    INSTANCE;

    @Override
    public AccountTransaction apply( AccountTransactionDTO dto ) {
        AccountTransaction entity = null;

        if ( Objects.nonNull( dto ) ) {
            entity = new AccountTransaction();
            BeanUtils.copyProperties( dto, entity );

            if ( Objects.nonNull( dto.getType() ) ) {
                TransactionType transactionType = Util.getTransactionTypes().get( dto.getType() );
                entity.setTransactionType( transactionType );
            }
        }

        return entity;
    }
}
