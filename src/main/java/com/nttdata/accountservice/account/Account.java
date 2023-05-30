package com.nttdata.accountservice.account;

import com.nttdata.accountservice.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@Document( collection = "accounts" )
public class Account implements Serializable {

    /**
     * Document's primary key
     */
    @Id
    private String idAccount;

    /**
     * Account's number
     */
    @Field( name = "NM_ACCOUNT" )
    private Integer nmAccount;

    /**
     * Account's available balance
     */
    @Field( name = "AVAILABLE_BALANCE" )
    private BigDecimal availableBalance;

    /**
     * Total commission for the account
     */
    @Field( name = "NM_COMMISSION" )
    private BigDecimal nmCommission;

    /**
     * Limit for the number of transactions
     */
    @Field( name = "MOVEMENT_LIMIT" )
    private Integer movementLimit;

    /**
     * Type of account
     */
    @Field( name = "ACCOUNT_TYPE" )
    private AccountType accountType;

    /**
     * Customer's identifier for the account
     */
    @Field( name = "CUSTOMER_ID" )
    private String customerId;
}
