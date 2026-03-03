package it.unibo.pps.e1;

import it.unibo.pps.e1.impl.CoreBankAccount;
import it.unibo.pps.e1.impl.GoldBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class implements a test suite for a {@code GoldBankAccount}.
 */
public class GoldBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    public void initTest() {
        initAccount(new GoldBankAccount(new CoreBankAccount()));
    }

    @Override
    @Test
    public void testWithdrawalIsPerformedIfPossible() {
        final int withdrawalAmount = 200;
        final int expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - withdrawalAmount;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        getAccount().withdraw(withdrawalAmount);
        assertEquals(expectedRemainingAmount, getAccount().getBalance());
    }

    @Test
    public void testCanWithdrawIfOverdraftIsLowerThanTheLimit() {
        final int withdrawalAmount = 1100;
        final int expectedBalance = -100;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        getAccount().withdraw(withdrawalAmount);
        assertEquals(expectedBalance, getAccount().getBalance());
    }

    @Test
    public void testCannotWithdrawIfOverdraftHigherThanTheLimit() {
        final int withdrawalAmount = FIRST_DEPOSIT_AMOUNT - GoldBankAccount.MAX_OVERDRAFT_AMOUNT + 1;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> getAccount().withdraw(withdrawalAmount));
    }

}
