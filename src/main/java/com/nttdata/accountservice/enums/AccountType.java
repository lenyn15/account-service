package com.nttdata.accountservice.enums;

/**
 * @author Lenyn Smith Goicochea Arévalo
 */
public enum AccountType {
    SAVING( "Saving" ),
    CURRENT_ACCOUNT( "Current Account" ),
    FIXED_TERM( "Fixed Term" );

    public final String label;

    AccountType( String label ) {
        this.label = label;
    }
}
