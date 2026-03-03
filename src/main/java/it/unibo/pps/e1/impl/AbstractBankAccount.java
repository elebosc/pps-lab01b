package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

/**
 * This class is an abstraction over the different types of bank account.
 */
public abstract class AbstractBankAccount implements BankAccount {

    private final BankAccount base;

    public AbstractBankAccount(BankAccount base) {
        this.base = base;
    }

    public int getBalance() {
        return base.getBalance();
    }

    private void checkAmountIsNotNegativeOrNull(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount cannot be negative or null.");
        }
    }

    public void deposit(int amount) {
        checkAmountIsNotNegativeOrNull(amount);
        base.deposit(amount);
    }

    public void withdraw(int amount) {
        checkAmountIsNotNegativeOrNull(amount);
        if (this.getBalance() - (amount + getFee(amount)) < getAllowedOverdraft()){
            throw new IllegalStateException("Cannot withdraw if the resulting balance is lower than " + getAllowedOverdraft());
        }
        base.withdraw(amount + getFee(amount));
    }

    protected abstract int getFee(int amount);

    protected abstract int getAllowedOverdraft();

}
