package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public class SilverBankAccount extends AbstractBankAccount {

    public final static int FEE = 1;

    public SilverBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    int getFee() {
        return FEE;
    }

    @Override
    int getAllowedOverdraft() {
        return 0;
    }

}
