package com.dollarsbank.utility;

public class ConsolePrinterUtility {

    public static void welcomePrinter() {
        System.out.println(ColorsUtility.ANSI_BLUE + "+---------------------------+");
        System.out.println("| DOLLARSBANK Welcomes You! |");  
        System.out.println("+---------------------------+" + ColorsUtility.ANSI_RESET); 
        System.out.println("1. Create New  Account");
        System.out.println("2. Login");
        System.out.println("3. Exit\n");
        System.out.println(ColorsUtility.ANSI_GREEN + "Enter Choice (1, 2 or 3): " + ColorsUtility.ANSI_RESET);
    }

    public static void createAccountPrinter() {
        System.out.println();
        System.out.println(ColorsUtility.ANSI_BLUE + "+-------------------------------+");
        System.out.println("| Enter Details For New Account |");
        System.out.println("+-------------------------------+" + ColorsUtility.ANSI_RESET);
    }

    public static void loginPrinter() {
        System.out.println(ColorsUtility.ANSI_BLUE + "+---------------------+");
        System.out.println("| Enter Login Details |");
        System.out.println("+---------------------+" + ColorsUtility.ANSI_RESET);
    }
    
    public static void loginWelcomePrinter() {
        System.out.println(ColorsUtility.ANSI_BLUE + "+---------------------+");
        System.out.println("| WELCOME Customer!!! |");
        System.out.println("+---------------------+" + ColorsUtility.ANSI_RESET);
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Funds Transfer");
        System.out.println("4. View 5 Recent Transactions");
        System.out.println("5. Display Customer Information");
        System.out.println("6. Sign Out\n");
        System.out.println(ColorsUtility.ANSI_GREEN + "Enter  Choice (1, 2, 3, 4, 5, or 6): " + ColorsUtility.ANSI_RESET);
    }

}
