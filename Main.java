
package com.mycompany.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create the account (PIN = 1234, starting balance = 1000) and the customer
        BankAccount account = new BankAccount(1234, 1000.0);
        Customer customer = new Customer("Remy", account);

        System.out.println("=== Welcome to the ATM ===");

        // ---------- STEP 1: AUTHENTICATION ----------
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < MAX_ATTEMPTS && !loggedIn) {
            System.out.print("Enter your PIN: ");
            int enteredPin = readInt(input);

            if (account.checkPin(enteredPin)) {
                loggedIn = true;
            } else {
                attempts++;
                System.out.println("Wrong PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many wrong attempts. Card blocked. Goodbye!");
            input.close();
            return;                                  // stop the program
        }

        System.out.println("Login successful. Hello, " + customer.getName() + "!");

        // ---------- STEP 2: MAIN MENU ----------
        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("------ MAIN MENU ------");
            System.out.println("1. Check balance");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");

            int choice = readInt(input);

            if (choice == 1) {
                System.out.println("Your balance is: " + account.getBalance());

            } else if (choice == 2) {
                System.out.print("Enter amount to deposit: ");
                double amount = readDouble(input);
                account.deposit(amount);

            } else if (choice == 3) {
                System.out.print("Enter amount to withdraw: ");
                double amount = readDouble(input);
                account.withdraw(amount);

            } else if (choice == 4) {
                account.showTransactions();

            } else if (choice == 5) {
                System.out.println("Thank you for using the ATM. Goodbye!");
                running = false;

            } else {
                System.out.println("Invalid option. Please choose 1 to 5.");
            }
        }

        input.close();
    }

    // ---------- Helper methods: keep asking until the user types a valid number ----------
    private static int readInt(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Please enter a whole number: ");
            input.next();                            
        }
        return input.nextInt();
    }

    private static double readDouble(Scanner input) {
        while (!input.hasNextDouble()) {
            System.out.print("Please enter a valid amount: ");
            input.next();
        }
        return input.nextDouble();
    }
}
