package com.dollarsbank.controller;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.SavingsAccount;
import com.dollarsbank.utility.DataGeneratorStubUtil;
import com.dollarsbank.utility.ColorsUtility;

public class DollarsBankController {
    static Scanner scanner = new Scanner(System.in);
    static boolean isValidChoice = false;
    static boolean isValidLoginChoice = false;

    public static int currentAccountId = 0; // Used to track logged in user

    static Map<Account, List<Float>> accountTransactionMap = new HashMap<>();
    static List<Float> transactionList = new ArrayList<>();

    public static void welcome() {
        // Arrange hard coded data for testing
        Account hardCodedAccount = DataGeneratorStubUtil.getAccount1();
        transactionList.add(hardCodedAccount.getCustInitialDepositAmount());
        accountTransactionMap.put(hardCodedAccount, transactionList);

        // Start at the welcome page
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
                System.out.println(ColorsUtility.ANSI_RED + "Please Enter a Valid Choice (1, 2, or 3): "
                        + ColorsUtility.ANSI_RESET);
            }
        } catch (InputMismatchException e) {
            System.out.println(ColorsUtility.ANSI_RED + "Please Enter a Valid Numeric Choice (1, 2, or 3): "
                    + ColorsUtility.ANSI_RESET);
        }

        return userChoice;
    }

    public static Account createAccount() {
        scanner.nextLine(); // Clear buffer
        ConsolePrinterUtility.createAccountPrinter();

        // Get and Validate Customer Name
        boolean validName = false;
        String custName = "";
        do {
            System.out.println("\nCustomer Name: ");
            custName = scanner.nextLine();
            validName = validateName(custName);
            if (!validName) {
                System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid name (i.e. 'Jim Bob')"
                        + ColorsUtility.ANSI_RESET);
            }
        } while (!validName);

        // Get and Validate Customer Address
        boolean validAddress = false;
        String custAddress = "";
        do {
            System.out.println("\nCustomer Address: ");
            custAddress = scanner.nextLine();
            validAddress = validateAddress(custAddress);
            if (!validAddress) {
                System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid address (i.e. '123 Street')\n"
                        + ColorsUtility.ANSI_RESET);
            }
        } while (!validAddress);

        // Get and Validate Customer Phone Number
        boolean validPhoneNumber = false;
        int custPhoneNumber = 0;
        do {
            System.out.println("\nCustomer Contact Number: ");
            try {
                custPhoneNumber = scanner.nextInt();
                validPhoneNumber = validatePhoneNumber(custPhoneNumber);
                if (!validPhoneNumber) {
                    System.out.println(
                            ColorsUtility.ANSI_RED + "\nPlease enter a valid ten digit phone number (i.e. 1234567890)"
                                    + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ColorsUtility.ANSI_RED
                        + "\nPlease enter a valid ten digit phone number (i.e. 1234567890)" + ColorsUtility.ANSI_RESET);
            }

            scanner.nextLine(); // Clear buffer
        } while (!validPhoneNumber);

        // Get and Validate Customer User Id
        boolean validIdFormat = false;
        boolean isTaken = false;
        int custId = 0;

        do {
            System.out.println("\nUser Id: ");
            try {
                custId = scanner.nextInt();
                validIdFormat = validateUserId(custId);
                if (!validIdFormat) {
                    System.out.println(ColorsUtility.ANSI_RED
                            + "\nPlease enter a valid user id greater than 0 (i.e. 15)" + ColorsUtility.ANSI_RESET);
                }

                // Check if userId is already taken
                isTaken = false;
                for (Account account : accountTransactionMap.keySet()) {
                    int accountId = account.getCustUserId();

                    if (accountId == custId) {
                        isTaken = true; // id is taken
                    }
                }
                if (isTaken) {
                    System.out.println(ColorsUtility.ANSI_RED + "\nPlease choose another user id, " + custId
                            + " is taken already!" + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid user id greater than 0 (i.e. 15)"
                        + ColorsUtility.ANSI_RESET);
            }
            scanner.nextLine();

        } while (!validIdFormat || isTaken);

        // Get and Validate Customer Password
        boolean validPassword = false;
        String password = "";

        do {
            System.out.println("\nPassword: 8 Characters with Lower, Upper, Number & Special");
            password = scanner.nextLine();
            validPassword = validatePassword(password);

            if (!validPassword) {
                System.out.println(ColorsUtility.ANSI_RED +
                        "\nPlease enter a password that has 8 characters with lower, upper, number, and special"
                        + ColorsUtility.ANSI_RESET);
            }
        } while (!validPassword);

        // Get and Validate Initial Deposit
        boolean validDepositInput = false;
        float initialDeposit = 0;

        do {
            System.out.println("\nInitial Deposit Amount: ");
            try {
                initialDeposit = scanner.nextFloat();
                validDepositInput = validateInitialDeposit(initialDeposit);
                if (!validDepositInput) {
                    System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid positive deposit amount"
                            + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid positive deposit amount"
                        + ColorsUtility.ANSI_RESET);
            }
            scanner.nextLine();

        } while (!validDepositInput);

        // Create a new account with all of the validated user input
        Account account = new Account(custName, custAddress, custPhoneNumber, custId, password, initialDeposit,
                initialDeposit);

        return account;
    }

    public static void login() {
        scanner.nextLine();
        boolean accountFound = false;
        int loginId = 0;

        do {
            ConsolePrinterUtility.loginPrinter();

            System.out.println("User Id: ");
            try {
                loginId = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(
                        ColorsUtility.ANSI_RED + "\nPlease enter a valid user id!\n" + ColorsUtility.ANSI_RESET);
                login();
            }

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
                System.out.println(
                        ColorsUtility.ANSI_RED + "\nInvalid Credentials. Try Again!" + ColorsUtility.ANSI_RESET);
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
                        System.out.println();
                        loginOptions();
                }

            } while (!isValidLoginChoice);
        } while (true);
    }

    private static int getUserLoginChoice() {
        int userChoice = 0;
        do {
            try {
                userChoice = scanner.nextInt();
                if (userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5
                        || userChoice == 6) {
                    isValidChoice = true;
                } else {
                    System.out.println(ColorsUtility.ANSI_RED + "Please Enter a Valid Choice (1, 2, 3, 4, 5, or 6): "
                            + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ColorsUtility.ANSI_RED
                        + "Please Enter a Valid Numeric Choice (1, 2, 3, 4, 5, or 6): " + ColorsUtility.ANSI_RESET);
            }
        } while (!isValidChoice);

        return userChoice;
    }

    private static void makeDeposit() {
        boolean isValidDeposit = false;
        float deposit = 0;

        do {
            System.out.println("Enter deposit amount: ");
            try {
                deposit = scanner.nextFloat();
                isValidDeposit = validateInitialDeposit(deposit);
                if (!isValidDeposit) {
                    System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter a valid deposit amount\n"
                            + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        ColorsUtility.ANSI_RED + "\nPlease enter a valid deposit amount\n" + ColorsUtility.ANSI_RESET);
                scanner.nextLine();
                makeDeposit();
            }
        } while (!isValidDeposit);

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(deposit);
                account.setCustBalance(account.getCustBalance() + deposit);
                System.out.println("\nDeposit successful!\n");
            }
        }

        loginOptions();
    }

    private static void makeWithdrawal() {
        float withdrawal = 0;
        boolean isValidWithdrawal = false;

        do {
            System.out.println("Enter withdrawal amount: ");

            try {
                withdrawal = scanner.nextFloat() * -1;
                isValidWithdrawal = validateInitialDeposit(withdrawal * -1);
                if (!isValidWithdrawal) {
                    System.out.println(ColorsUtility.ANSI_RED + "\nPlease enter valid withdrawal amount\n"
                            + ColorsUtility.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(
                        ColorsUtility.ANSI_RED + "\nPlease enter valid withdrawal amount\n" + ColorsUtility.ANSI_RESET);
                scanner.nextLine();
                makeWithdrawal();
            }
        } while (!isValidWithdrawal);

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(withdrawal);
                account.setCustBalance(account.getCustBalance() + withdrawal);
                System.out.println("\nWithdrawal successful!\n");
            }
        }

        loginOptions();
    }

    private static void transferFunds() { // TODO: Validation (make sure user enters valid amount and doesn't go over
                                          // their balance)
        System.out.println("Enter how much you would like to transfer to your savings: ");
        float transfer = scanner.nextFloat() * -1;
        SavingsAccount savings = new SavingsAccount();

        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                accountTransactionMap.get(account).add(transfer);
                account.setCustBalance(account.getCustBalance() + transfer);

                savings = new SavingsAccount(userId, account.getCustPassword(),
                        savings.getCustBalance() + (transfer * -1));

                System.out.println("\nTransfer successful!\n");
            }
        }

        loginOptions();
    }

    private static void viewRecentTransactions() {
        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                List<Float> transactionList = accountTransactionMap.get(account);
                System.out.println();

                for (int i = transactionList.size() - 1; i >= 0; i--) {
                    if (transactionList.get(i) > 0.0) {
                        System.out.println("Deposit of $" + transactionList.get(i) + ".");
                    } else if (transactionList.get(i) < 0.0) {
                        System.out.println("Withdrawal or Transfer of $" + transactionList.get(i) * -1 + ".");
                    } else {
                        System.out.println("There was an operation, but of $0.00.");
                    }
                }

                System.out.println("Customer Balance - " + account.getCustBalance() + "\n");
            }
        }

        loginOptions();
    }

    private static void displayCustInfo() {
        for (Account account : accountTransactionMap.keySet()) {
            int userId = account.getCustUserId();

            if (currentAccountId == userId) {
                System.out.println("\nCustomer Name: " + account.getCustName());
                System.out.println("Customer Address: " + account.getCustAddress());
                System.out.println("Customer Phone: " + account.getCustPhoneNumber());
                System.out.println("Customer Balance: " + account.getCustBalance() + "\n");
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

    private static boolean validatePhoneNumber(int number) {
        String num = Integer.toString(number);
        return num.matches("[0-9]{10}");
    }

    private static boolean validateUserId(int number) {
        String num = Integer.toString(number);
        return num.matches("[1-9]+");
    }

    private static boolean validatePassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$");
    }

    private static boolean validateInitialDeposit(float number) {
        String num = Float.toString(number);
        return num.matches("^(?=.+)(?:[1-9]\\d*|0)?(?:\\.\\d+)?$");
    }
}
