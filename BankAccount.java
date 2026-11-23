/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
/**
 *
 * @author Remyboi
 */

public class BankAccount {

    // ----- Private data (hidden from other classes) -----
    private double balance;
    private final int pin;                       
    private String[] transactions;               
    private int transactionCount;                

    // ----- Constructor -----
    public BankAccount(int pin, double startingBalance) {
        this.pin = pin;
        this.balance = startingBalance;
        this.transactions = new String[100];     
        this.transactionCount = 0;
    }

    // ----- Authentication -----
    // Returns true if the PIN typed by the user matches the stored PIN
    public boolean checkPin(int enteredPin) {
        return enteredPin == pin;
    }

    // ----- Getter (read-only access to the balance) -----
    public double getBalance() {
        return balance;
    }

    // ----- Deposit -----
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: deposit amount must be greater than 0.");
            return;
        }
        balance = balance + amount;
        saveTransaction("Deposit    : +" + amount);
        System.out.println("Deposit successful. New balance: " + balance);
    }

    // ----- Withdraw -----
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Error: withdrawal amount must be greater than 0.");
        } else if (amount > balance) {
            // Not enough money -> show an error and do NOT record anything
            System.out.println("Error: insufficient balance. Your balance is " + balance);
        } else {
            balance = balance - amount;
            saveTransaction("Withdrawal : -" + amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
        }
    }

    // ----- Save a transaction in the array -----
    private void saveTransaction(String text) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount] = text;
            transactionCount++;
        } else {
            System.out.println("Warning: transaction history is full.");
        }
    }

    // ----- Show the history using a for loop -----
    public void showTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("----- Transaction History -----");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + transactions[i]);
        }
    }
}
