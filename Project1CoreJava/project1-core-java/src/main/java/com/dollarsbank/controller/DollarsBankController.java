package com.dollarsbank.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.SavingsAccount;

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

        boolean validName = false;
        String custName = "";
        do {
            System.out.println("\nCustomer Name: ");
            custName = scanner.nextLine();
            validName = validateName(custName);
            if (!validName) {
                System.out.println("\nPlease enter a valid name (i.e. 'Jim Bob')");
            }
        } while (!validName);
        

        boolean validAddress = false;
        String custAddress = "";
        do {
            System.out.println("\nCustomer Address: ");
            custAddress = scanner.nextLine();
            validAddress = validateAddress(custAddress);
            if (!validAddress) {
                System.out.println("\nPlease enter a valid address (i.e. '123 Street')\n");
            }
        } while (!validAddress);
        

        System.out.println("\nCustomer Contact Number: ");
        int custPhoneNumber = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.println("\nUser Id: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nPassword: 8 Characters with Lower, Upper & Special");
        String password = scanner.nextLine();

        System.out.println("\nInitial Deposit Amount: ");
        float initialDeposit = scanner.nextFloat();
        scanner.nextLine(); // TODO: Probaly can get rid of this

        Account account = new Account(custName, custAddress, custPhoneNumber, custId, password, initialDeposit,
                initialDeposit);

        return account;
    }

    public static void login() { // TODO: test this
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
                        makeWithdrawal();
                        break;
                    case 3:
                        // Transfer Funds
                        transferFunds();
                        break;
                    case 4:
                        // View 5 Recent Transactions
                        viewRecentTransactions();
                        break;
                    case 5:
                        // Display Customer Information
                        displayCustInfo();
                        break;
                    case 6:
                        welcome();
                    default:
                        System.out.println("Invalid Choice!");
                }

            } while (!isValidLoginChoice);
        } while (true);
    }

    private static int getUserLoginChoice() {
        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
            if (userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5
                    || userChoice == 6) {
                isValidChoice = true;
            } else {
                System.out.println("Please Enter a Valid Choice (1, 2, 3, 4, 5, or 6): ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Numeric Choice (1, 2, 3, 4, 5, or 6): ");
        }

        return userChoice;
    }

    private static void makeDeposit() { // TODO: Validation (Make sure deposit is positive value)
        System.out.println("Enter deposit amount: ");
        float deposit = scanner.nextFloat();

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(deposit);
                account.setCustBalance(account.getCustBalance() + deposit);
                System.out.println("\nDeposit successful!\n");
            }
        }

        // for (Account account : accountTransactionMap.keySet()) {
        // System.out.println(account.getCustBalance());
        // System.out.println(accountTransactionMap.get(account).toString());
        // }

        loginOptions();
    }

    private static void makeWithdrawal() { // TODO: Validation
        System.out.println("Enter withdrawal amount: ");
        float withdrawal = scanner.nextFloat() * -1;

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(withdrawal);
                account.setCustBalance(account.getCustBalance() + withdrawal);
                System.out.println("\nWithdrawal successful!\n");
            }
        }

        for (Account account : accountTransactionMap.keySet()) {
        System.out.println(account.getCustBalance());
        System.out.println(accountTransactionMap.get(account).toString());
        }

        loginOptions();
    }

    private static void transferFunds() {  //TODO: Validation (make sure user enters valid amount and doesn't go over their balance)
        System.out.println("Enter how much you would like to transfer to your savings: ");
        float transfer = scanner.nextFloat() * -1;
        SavingsAccount savings = new SavingsAccount();

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(transfer);
                account.setCustBalance(account.getCustBalance() + transfer);

                savings = new SavingsAccount(userId, account.getCustPassword(), savings.getCustBalance() + (transfer * -1));

                System.out.println("\nTransfer successful!\n");
            }
        }

        // for (Account account : accountTransactionMap.keySet()) {
        //     System.out.println(account.getCustBalance());
        //     System.out.println(accountTransactionMap.get(account).toString());
        // }

        // System.out.println(savings.toString());

        loginOptions();
    }

    private static void viewRecentTransactions() {
        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                List<Float> transactionList = accountTransactionMap.get(account);

                for (int i = transactionList.size() - 1; i >= 0; i--) {
                    if (transactionList.get(i) > 0.0) {
                        System.out.println("Deposit of $" + transactionList.get(i) + ".");
                    }
                    else if (transactionList.get(i) < 0.0) {
                        System.out.println("Withdrawal or Transfer of $" + transactionList.get(i) * -1 + ".");
                    }
                    else {
                        System.out.println("There was an operation, but of $0.00.");
                    }
                }

                System.out.println("Balance - " + account.getCustBalance());
            }
        }

        loginOptions();
    }

    private static void displayCustInfo() {
        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                System.out.println("Customer Name: " + account.getCustName());
                System.out.println("Customer Address: " + account.getCustAddress());
                System.out.println("Customer Phone: " + account.getCustPhoneNumber());
                System.out.println("Customer Balance: " + account.getCustBalance());
            }
        }

        loginOptions();
    }

    private static boolean validateAddress(String address) {
        return address.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)");
    }

    private static boolean validateName(String name) {
        return name.matches("^[A-Z]\\w+\\s[A-Z]\\w+");
    }
}
