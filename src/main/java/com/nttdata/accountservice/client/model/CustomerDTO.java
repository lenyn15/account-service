package com.nttdata.accountservice.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
@Getter
@Builder
@JsonInclude( Include.NON_NULL )
public class CustomerDTO implements Serializable {

    /**
     * Document's primary key
     */
    private String idCustomer;

    /**
     * Customer's name
     */
    private String dsName;

    /**
     * Type of customer
     */
    private Integer type;

    /**
     * Type of customer description
     */
    private String customerType;
}
