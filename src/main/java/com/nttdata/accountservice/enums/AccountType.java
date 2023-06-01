package com.nttdata.accountservice.enums;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountType {
    SAVING( "Ahorro" ),
    CURRENT_ACCOUNT( "Corriente" ),
    FIXED_TERM( "Plazo fijo" );

    public final String label;

    AccountType( String label ) {
        this.label = label;
    }
}
