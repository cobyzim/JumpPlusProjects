package com.dollarsbank.utility;

import com.dollarsbank.model.Account;

public class DataGeneratorStubUtil {
    private static Account account = new Account("Jim Bob", "123 Street", 1234567890, 
        1, "P@$$w0rd", 500, 500);

    public static Account getAccount1() {
        return account;
    }

}
