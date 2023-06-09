package com.nttdata.accountservice.util;

public final class Constants {

    private Constants() {
    }

    public static final class Key {

        private Key() {
        }

        public static final String INFO = "info";

        public static final String MESSAGE = "message";
    }

    public static final class CustomerType {

        private CustomerType() {
        }

        public static final String PERSONAL = "PERSONAL";

        public static final String BUSINESS = "BUSINESS";
    }

    public static final class AccountValue {

        private AccountValue() {
        }

        public static final Integer LENGTH = 14;

        public static final String IDENTIFIER = "1234";
    }

    public static final class AccountType {

        private AccountType() {
        }

        public static final Integer SAVING = 1;

        public static final Integer CURRENT_ACCOUNT = 2;

        public static final Integer FIXED_TERM = 3;
    }

    public static final class TransactionType {

        private TransactionType() {
        }

        public static final Integer DEPOSIT = 1;

        public static final Integer WITHDRAWAL = 2;
    }
}
