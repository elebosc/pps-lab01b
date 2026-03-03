package it.unibo.pps.e1.api;

/**
 * This interface describes the possible interactions with a bank account.
 */
public interface BankAccount {

    /**
     * Gets the current balance of the bank account.
     *
     * @return The current balance.
     */
    int getBalance();

    /**
     * Deposits the passed amount into the bank account.
     *
     * @param amount The amount to deposit.
     * @throws IllegalArgumentException if the amount is negative or null.
     */
    void deposit(int amount);

    /**
     * Withdraws the passed amount from the bank account, applying also a fee if the specific type
     * of bank account prescribes to do so.
     *
     * @param amount The amount to withdraw.
     * @throws IllegalArgumentException if the amount is negative or null.
     * @throws IllegalStateException if the resulting balance given the amount is lower than
     * the allowed overdraft.
     */
    void withdraw(int amount);

}