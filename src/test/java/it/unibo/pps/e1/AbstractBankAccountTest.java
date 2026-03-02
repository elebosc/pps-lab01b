package it.unibo.pps.e1;

import it.unibo.pps.e1.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractBankAccountTest {

    protected final int FIRST_DEPOSIT_AMOUNT = 1000;

    private BankAccount account;

    protected void initAccount(BankAccount account) {
        this.account = account;
    }

    protected BankAccount getAccount() {
        return this.account;
    }

    @BeforeEach
    abstract void initTest();

    @Test
    public void testInitiallyEmpty() {
        final int expectedInitialBalance = 0;
        assertEquals(expectedInitialBalance, this.account.getBalance());
    }

    @Test
    public void testCanDeposit() {
        this.account.deposit(FIRST_DEPOSIT_AMOUNT);
        assertEquals(FIRST_DEPOSIT_AMOUNT, this.account.getBalance());
    }

    @Test
    public abstract void testWithdrawalIsPerformedIfPossible();

    @Test
    public void testCannotDepositNegativeAmount() {
        final int negativeAmount = -100;
        assertThrows(IllegalArgumentException.class, () -> this.account.deposit(negativeAmount));
    }

    @Test
    public void testCannotDepositNullAmount() {
        final int nullAmount = 0;
        assertThrows(IllegalArgumentException.class, () -> this.account.deposit(nullAmount));
    }

    @Test
    public void testCannotWithdrawNegativeAmount() {
        final int negativeAmount = -100;
        assertThrows(IllegalArgumentException.class, () -> this.account.withdraw(negativeAmount));
    }

    @Test
    public void testCannotWithdrawNullAmount() {
        final int nullAmount = 0;
        assertThrows(IllegalArgumentException.class, () -> this.account.withdraw(nullAmount));
    }

}
