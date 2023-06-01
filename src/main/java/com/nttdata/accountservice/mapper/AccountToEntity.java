package com.nttdata.accountservice.mapper;

import com.nttdata.accountservice.enums.AccountType;
import com.nttdata.accountservice.lib.account.Account;
import com.nttdata.accountservice.lib.account.AccountDTO;
import com.nttdata.accountservice.util.Util;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountToEntity implements Function<AccountDTO, Account> {

    INSTANCE;

    @Override
    public Account apply( AccountDTO dto ) {
        Account entity = null;

        if ( Objects.nonNull( dto ) ) {
            entity = new Account();
            BeanUtils.copyProperties( dto, entity );

            if ( Objects.nonNull( dto.getType() ) ) {
                AccountType accountType = Util.getAccountTypes().get( dto.getType() );
                entity.setAccountType( accountType );
                this.assignLimitAndMaintenance( entity );
            }

            if ( Objects.nonNull( dto.getCustomerDTO() ) ) {
                entity.setIdAccount( dto.getCustomerDTO().getIdCustomer() );
            }
        }

        return entity;
    }

    private void assignLimitAndMaintenance( Account entity ) {
        EnumMap<AccountType, Integer> limits = new EnumMap<>( AccountType.class );
        limits.put( AccountType.SAVING, 5 );
        limits.put( AccountType.CURRENT_ACCOUNT, -1 );
        limits.put( AccountType.FIXED_TERM, 1 );

        entity.setMovementLimit( limits.get( entity.getAccountType() ) );
        entity.setNmCommission( BigDecimal.ZERO );

        if ( AccountType.CURRENT_ACCOUNT.equals( entity.getAccountType() ) ) {
            entity.setNmCommission( new BigDecimal( "15.0" ) );
        }
    }
}
