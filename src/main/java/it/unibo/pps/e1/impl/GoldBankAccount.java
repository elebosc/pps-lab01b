package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public class GoldBankAccount extends AbstractBankAccount {

    public static final int MAX_OVERDRAFT_AMOUNT = -500;

    public GoldBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    int getFee() {
        return 0;
    }

    @Override
    int getAllowedOverdraft() {
        return MAX_OVERDRAFT_AMOUNT;
    }

}
