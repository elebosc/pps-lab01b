package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public class SilverBankAccount implements BankAccount {

    public final static int FEE = 1;

    private final CoreBankAccount base;

    public SilverBankAccount(CoreBankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposited amount cannot be negative or null.");
        }
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawn amount cannot be negative or null.");
        }
        if (this.getBalance() < amount + FEE){
            throw new IllegalStateException("Cannot withdraw more than the available balance.");
        }
        base.withdraw(amount + FEE);
    }

}
