package com.nttdata.accountservice.util;

import com.nttdata.accountservice.enums.AccountType;
import com.nttdata.accountservice.enums.TransactionType;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Util {

    private Util() {
    }

    public static String generateNumAccount() {
        Random rand = new Random();
        int limit = Constants.AccountValue.LENGTH - Constants.AccountValue.IDENTIFIER.length();

        String digits = IntStream.range( 0, limit )
                .mapToObj( digit -> String.valueOf( rand.nextInt( limit ) ) )
                .collect( Collectors.joining() );

        return Constants.AccountValue.IDENTIFIER + digits;
    }

    /**
     * Pass to a map any type of object like List or Class
     *
     * @param object the reference object to pass to the map
     * @return Map<String, Object>
     */
    public static Map<String, Object> getData( Object object ) {
        Map<String, Object> data = new HashMap<>();
        data.put( Constants.Key.INFO, object );

        return data;
    }

    /**
     * Pass to a map the message to return
     *
     * @param message String with success message
     * @return Map<String, String>
     */
    public static Map<String, String> getMessageSuccess( String message ) {
        Map<String, String> data = new HashMap<>();
        data.put( Constants.Key.MESSAGE, message );

        return data;
    }

    public static Map<Integer, AccountType> getAccountTypes() {
        Map<Integer, AccountType> types = new HashMap<>();
        types.put( Constants.AccountType.SAVING, AccountType.SAVING );
        types.put( Constants.AccountType.CURRENT_ACCOUNT, AccountType.CURRENT_ACCOUNT );
        types.put( Constants.AccountType.FIXED_TERM, AccountType.FIXED_TERM );

        return types;
    }

    public static Map<Integer, TransactionType> getTransactionTypes() {
        Map<Integer, TransactionType> types = new HashMap<>();
        types.put( Constants.TransactionType.WITHDRAWAL, TransactionType.WITHDRAWAL );
        types.put( Constants.TransactionType.DEPOSIT, TransactionType.DEPOSIT );

        return types;
    }
}
