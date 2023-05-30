package com.nttdata.accountservice.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
    private Integer nmAccount;

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
     * Customer's identifier
     */
    private String idCustomer;
}
