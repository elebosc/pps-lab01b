package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

/**
 * This class provides the very core operations of a bank account.
 */
public class CoreBankAccount implements BankAccount {

    private int balance = 0;

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }

}
