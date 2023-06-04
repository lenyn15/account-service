package com.nttdata.accountservice.lib.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nttdata.accountservice.client.customer.CustomerDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@JsonInclude( Include.NON_NULL )
public class AccountDTO implements Serializable {

    /**
     * Document's primary key
     */
    private String idAccount;

    /**
     * Account's number
     */
    private String nmAccount;

    /**
     * Account's available balance
     */
    private BigDecimal availableBalance;

    /**
     * Total commission for the account
     */
    private BigDecimal nmCommission;

    /**
     * Limit for the number of transactions
     */
    private Integer movementLimit;

    /**
     * Type of account
     */
    @NotNull( message = "Debe indicar el tipo de cuenta" )
    private Integer type;

    /**
     * Type of account description
     */
    private String accountType;

    /**
     * Customer for the account
     */
    private CustomerDTO customerDTO;

    /**
     * Number of holders of the account
     */
    private Integer nmHolders;

    /**
     * Number of signatory of the account
     */
    private Integer nmSignatories;
}
