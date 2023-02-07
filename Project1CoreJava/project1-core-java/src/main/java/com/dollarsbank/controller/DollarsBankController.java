package com.dollarsbank.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.model.Account;

public class DollarsBankController {
    static Scanner scanner = new Scanner(System.in);
    static boolean isValidChoice = false;

    static Map<Account, List<Float>> accountTransactionMap = new HashMap<>();
    static List<Float> transactionList = new ArrayList<>();

    public static void welcome() {

        do {
            ConsolePrinterUtility.welcomePrinter();

            int userChoice = 0;
            do {
                userChoice = getUserChoice();
                if (userChoice == 1) {
                    Account account = createAccount();
                    transactionList.add(account.getCustInitialDepositAmount()); // put initial deposit into list
                    accountTransactionMap.put(account, transactionList); // insert account and transaction list into
                                                                         // hashmap
                }
                if (userChoice == 2) {
                    // LOGIN
                }
                if (userChoice == 3) {
                    System.exit(0);
                }

                scanner.nextLine(); // Used to clear buffer
            } while (!isValidChoice);
        } while (true);
    }

    public static int getUserChoice() {
        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
            if (userChoice == 1 || userChoice == 2 || userChoice == 3) {
                isValidChoice = true;
            } else {
                System.out.println("Please Enter a Valid Choice (1, 2, or 3): ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Numeric Choice (1, 2, or 3): ");
        }

        return userChoice;
    }

    public static Account createAccount() {
        scanner.nextLine(); // Clear buffer
        ConsolePrinterUtility.createAccountPrinter();
        System.out.println("Customer Name: ");
        String custName = scanner.nextLine();

        System.out.println("Customer Address: ");
        String custAddress = scanner.nextLine();

        System.out.println("Customer Contact Number: ");
        int custPhoneNumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.println("User Id: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Password: 8 Characters with Lower, Upper & Special");
        String password = scanner.nextLine();

        System.out.println("Initial Deposit Amount: ");
        float initialDeposit = scanner.nextFloat();
        scanner.nextLine();

        Account account = new Account(custName, custAddress, custPhoneNumber, custId, password, initialDeposit);

        return account;
    }

}
