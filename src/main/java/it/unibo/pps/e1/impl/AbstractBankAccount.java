package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public abstract class AbstractBankAccount implements BankAccount {

    private final BankAccount base;

    public AbstractBankAccount(BankAccount base) {
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
        if (this.getBalance() - (amount + getFee()) < getAllowedOverdraft()){
            throw new IllegalStateException("Cannot withdraw if the resulting balance is lower than " + getAllowedOverdraft());
        }
        base.withdraw(amount + getFee());
    }

    abstract int getFee();

    abstract int getAllowedOverdraft();

}
