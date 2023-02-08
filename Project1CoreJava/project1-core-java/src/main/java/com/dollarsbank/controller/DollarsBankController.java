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
    static boolean isValidLoginChoice = false;

    public static int currentAccountId = 0;

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
                    login();
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

    public static Account createAccount() { // TODO: Need validation here
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
        scanner.nextLine();  //TODO: Probaly can get rid of this

        Account account = new Account(custName, custAddress, custPhoneNumber, custId, password, initialDeposit, initialDeposit);

        return account;
    }

    public static void login() {  //TODO: test this
        scanner.nextLine();
        boolean accountFound = false;

        do {
            ConsolePrinterUtility.loginPrinter();

            System.out.println("User Id: ");
            int loginId = scanner.nextInt(); // TODO: need validation here
            scanner.nextLine();

            System.out.println("Password: ");
            String loginPassword = scanner.nextLine();

            for (Account account : accountTransactionMap.keySet()) {
                int userId = account.getCustUserId();
                String userPwd = account.getCustPassword();

                if (userId == loginId && userPwd.equals(loginPassword)) {
                    accountFound = true;
                    currentAccountId = loginId;
                    break;
                }
            }

            if (accountFound) {
                loginOptions();

            } else {
                System.out.println("Invalid Credentials. Try Again!");
            }
        } while (!accountFound);
    }

    public static void loginOptions() {
        do {
            ConsolePrinterUtility.loginWelcomePrinter();

            int userChoice = 0;
            do {
                userChoice = getUserLoginChoice();
                switch (userChoice) {
                    case 1:
                        // Make Deposit
                        makeDeposit();
                        break;
                    case 2:
                        // Make Withdrawal
                        break;
                    case 3:
                        // Transfer Funds
                        break;
                    case 4:
                        // View 5 Recent Transactions
                        break;
                    case 5:
                        // Display Customer Information
                        break;
                    case 6:
                        welcome();
                    default:
                        System.out.println("Invalid Choice!");
                }

            } while(!isValidLoginChoice);
        } while(true);
    }

    private static int getUserLoginChoice() {
        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
            if (userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5 || userChoice == 6) {
                isValidChoice = true;
            } else {
                System.out.println("Please Enter a Valid Choice (1, 2, or 3): ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Numeric Choice (1, 2, or 3): ");
        }

        return userChoice;
    }

    public static void makeDeposit() { // TODO: Validation (Make sure deposit is positive value)
        System.out.println("Enter deposit amount: ");
        float deposit = scanner.nextFloat();

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(deposit);
                account.setCustBalance(account.getCustBalance() + deposit);
                System.out.println("Deposit successful!");
            }
        }

        // for (Account account : accountTransactionMap.keySet()) {
        //     System.out.println(account.getCustBalance());
        //     System.out.println(accountTransactionMap.get(account).toString());
        // }

        loginOptions();
    }

}
