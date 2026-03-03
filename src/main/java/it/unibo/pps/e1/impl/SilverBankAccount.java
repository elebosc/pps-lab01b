package it.unibo.pps.e1.impl;

import it.unibo.pps.e1.api.BankAccount;

/**
 * This class implements a bank account that applies a fee on withdrawal
 * and allows no overdraft.
 */
public class SilverBankAccount extends AbstractBankAccount {

    public final static int FEE = 1;

    public SilverBankAccount(BankAccount base) {
        super(base);
    }

    @Override
    protected int getFee(int amount) {
        return FEE;
    }

    @Override
    protected int getAllowedOverdraft() {
        return 0;
    }

}
