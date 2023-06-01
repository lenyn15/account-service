package com.nttdata.accountservice.mapper;

import com.nttdata.accountservice.lib.account.Account;
import com.nttdata.accountservice.lib.account.AccountDTO;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountToDTO implements Function<Account, AccountDTO> {

    INSTANCE;

    @Override
    public AccountDTO apply( Account entity ) {
        AccountDTO dto = new AccountDTO();

        if ( Objects.nonNull( entity ) ) {
            BeanUtils.copyProperties( entity, dto );

            if ( Objects.nonNull( entity.getAccountType() ) ) {
                dto.setAccountType( entity.getAccountType().name() );
            }
        }

        return dto;
    }
}
