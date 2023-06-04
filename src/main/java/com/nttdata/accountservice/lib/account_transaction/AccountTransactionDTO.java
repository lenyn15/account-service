package com.nttdata.accountservice.lib.account_transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Setter
@JsonInclude( Include.NON_NULL )
public class AccountTransactionDTO {

    /**
     * Document's primary key
     */
    private String idAccountTransaction;

    /**
     * Account's identifier
     */
    @NotNull( message = "Debe elegir la cuenta a la que se hará la transacción" )
    private String idAccount;

    /**
     * Customer's identifier
     */
    private String customerId;

    /**
     * Account's number where transaction is made
     */
    private String nmAccount;

    /**
     * Type of transaction
     */
    @NotNull( message = "Debe elegir el tipo de transacción" )
    private Integer type;

    /**
     * Type of transaction description
     */
    private String transactionType;

    /**
     * Transaction amount
     */
    @NotNull( message = "Debe ingresar el monto para realizar la transacción" )
    private BigDecimal nmAmount;

    /**
     * Transaction date
     */
    private LocalDateTime fcTransaction;
}
