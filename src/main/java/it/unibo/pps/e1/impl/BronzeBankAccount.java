package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

public class BronzeBankAccount extends AbstractBankAccount {

    public static final int MIN_WITHDRAWAL_AMOUNT_WITH_FEE = 100;
    public static final int FEE = 1;

    public BronzeBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    protected int getFee(int amount) {
        return (amount < MIN_WITHDRAWAL_AMOUNT_WITH_FEE) ? 0 : FEE;
    }

    @Override
    protected int getAllowedOverdraft() {
        return 0;
    }

}
