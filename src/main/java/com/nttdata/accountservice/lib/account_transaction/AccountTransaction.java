package com.nttdata.accountservice.lib.account_transaction;

import com.nttdata.accountservice.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@Document( collection = "account_transactions" )
public class AccountTransaction {

    /**
     * Document's primary key
     */
    @Id
    private String idAccountTransaction;

    /**
     * Customer's identifier
     */
    @Field( "CUSTOMER_ID" )
    private String customerId;

    /**
     * Account's number where transaction is made
     */
    @Field( name = "NM_ACCOUNT" )
    private String nmAccount;

    /**
     * Type of transaction
     */
    @Field( name = "TRANSACTION_TYPE" )
    private TransactionType transactionType;

    /**
     * Transaction amount
     */
    @Field( name = "NM_AMOUNT" )
    private BigDecimal nmAmount;

    /**
     * Transaction date
     */
    @Field( name = "FC_TRANSACTION" )
    private LocalDateTime fcTransaction;
}
