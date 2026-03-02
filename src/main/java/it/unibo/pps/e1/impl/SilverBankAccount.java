package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public class SilverBankAccount implements BankAccount {

    private final static int FEE = 1;

    private final CoreBankAccount base;

    public SilverBankAccount(CoreBankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    public void deposit(int amount) {
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        if (this.getBalance() < amount + FEE){
            throw new IllegalStateException();
        }
        base.withdraw(amount + FEE);
    }
}
