package com.dollarsbank.utility;

public class ConsolePrinterUtility {

    public static void welcomePrinter() {
        System.out.println("+---------------------------+");
        System.out.println("| DOLLARSBANK Welcomes You! |");  
        System.out.println("+---------------------------+"); 
        System.out.println("1. Create New  Account");
        System.out.println("2. Login");
        System.out.println("3. Exit\n");
        System.out.println("Enter Choice (1, 2 or 3): ");
    }

    public static void createAccountPrinter() {
        System.out.println();
        System.out.println("+-------------------------------+");
        System.out.println("| Enter Details For New Account |");
        System.out.println("+-------------------------------+");
    }

    public static void loginPrinter() {
        System.out.println("+---------------------+");
        System.out.println("| Enter Login Details |");
        System.out.println("+---------------------+");
    }
    
    public static void loginWelcomePrinter() {
        System.out.println("+---------------------+");
        System.out.println("| WELCOME Customer!!! |");
        System.out.println("+---------------------+");
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Funds Transfer");
        System.out.println("4. View 5 Recent Transactions");
        System.out.println("5. Display Customer Information");
        System.out.println("6. Sign Out\n");
        System.out.println("Enter  Choice (1, 2, 3, 4, 5, or 6): ");
    }

}
