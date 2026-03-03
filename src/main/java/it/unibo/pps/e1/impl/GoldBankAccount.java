package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

/**
 * This class implements a bank account that applies no fees on withdrawal
 * and allows a limited overdraft.
 */
public class GoldBankAccount extends AbstractBankAccount {

    public static final int MAX_OVERDRAFT_AMOUNT = -500;

    public GoldBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    protected int getFee(int amount) {
        return 0;
    }

    @Override
    protected int getAllowedOverdraft() {
        return MAX_OVERDRAFT_AMOUNT;
    }

}
