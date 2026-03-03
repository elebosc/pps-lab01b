package it.unibo.pps.e1;

import it.unibo.pps.e1.impl.CoreBankAccount;
import it.unibo.pps.e1.impl.SilverBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class implements a test suite for a {@code SilverBankAccount}.
 */
public class SilverBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    public void initTest() {
        initAccount(new SilverBankAccount(new CoreBankAccount()));
    }

    @Override
    @Test
    public void testWithdrawalIsPerformedIfPossible() {
        final int withdrawalAmount = 200;
        final int expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (withdrawalAmount + SilverBankAccount.FEE);
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        getAccount().withdraw(withdrawalAmount);
        assertEquals(expectedRemainingAmount, getAccount().getBalance());
    }

    @Test
    public void testOverdraftIsNotAllowed() {
        final int withdrawalAmount = 1200;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> getAccount().withdraw(withdrawalAmount));
    }

    @Test
    public void testWithdrawalCannotExceedWithFee() {
        final int withdrawalAmount = FIRST_DEPOSIT_AMOUNT + SilverBankAccount.FEE;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> getAccount().withdraw(withdrawalAmount));
    }

}
