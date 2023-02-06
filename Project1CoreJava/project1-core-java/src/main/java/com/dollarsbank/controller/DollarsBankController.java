package com.dollarsbank.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dollarsbank.utility.ConsolePrinterUtility;

public class DollarsBankController {
    static Scanner scanner = new Scanner(System.in);
    static boolean isValidChoice = false;

    public static void welcome() {
        ConsolePrinterUtility.welcomePrinter();

        int userChoice = 0;
        do {
            userChoice = getUserChoice();
            //System.out.println(userChoice);

            scanner.nextLine(); //Used to clear buffer
        } while (!isValidChoice);

    }

    public static int getUserChoice() {
        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
            if (userChoice == 1 || userChoice == 2 || userChoice == 3) {
                isValidChoice = true;
            }
            else {
                System.out.println("Please Enter a Valid Choice (1, 2, or 3): ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Numeric Choice (1, 2, or 3): ");
        }

        // int userChoice = scanner.nextInt();
        // if (userChoice == 1 || userChoice == 2 || userChoice == 3) {
        // isValidChoice = true;
        // }

        // return userChoice;

        // while (userChoice != 1 && userChoice != 2 && userChoice != 3) {
        // System.out.println("Please Enter a Valid Choice (1, 2, or 3): ");
        // userChoice = scanner.nextInt();
        // }
        return userChoice;

    }
}
