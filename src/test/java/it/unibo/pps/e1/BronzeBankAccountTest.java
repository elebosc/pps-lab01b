package it.unibo.pps.e1;

import it.unibo.pps.e1.impl.BronzeBankAccount;
import it.unibo.pps.e1.impl.CoreBankAccount;
import it.unibo.pps.e1.impl.GoldBankAccount;
import it.unibo.pps.e1.impl.SilverBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * This class implements a test suite for a {@code BronzeBankAccount}.
 */
public class BronzeBankAccountTest extends AbstractBankAccountTest {

    @BeforeEach
    public void initTest() {
        initAccount(new BronzeBankAccount(new CoreBankAccount()));
    }

    @Override
    @Test
    public void testWithdrawalIsPerformedIfPossible() {
        final int withdrawalAmount = 50;
        final int expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - withdrawalAmount;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        getAccount().withdraw(withdrawalAmount);
        assertEquals(expectedRemainingAmount, getAccount().getBalance());
    }

    @Test
    public void testFeeIsAppliedIfWithdrawalExceedsTheLimit() {
        final int withdrawalAmount = 200;
        final int expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (withdrawalAmount + BronzeBankAccount.FEE);
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        getAccount().withdraw(withdrawalAmount);
        assertEquals(expectedRemainingAmount, getAccount().getBalance());
    }

    @Test
    public void testOverdraftIsNotAllowed() {
        final int exceedingAmount = 200;
        final int withdrawalAmount = FIRST_DEPOSIT_AMOUNT + exceedingAmount;
        getAccount().deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> getAccount().withdraw(withdrawalAmount));
    }

    @Test
    public void testWithdrawalCannotExceedWithFee() {
        final int depositAmount = FIRST_DEPOSIT_AMOUNT + BronzeBankAccount.MIN_WITHDRAWAL_AMOUNT_WITH_FEE;
        final int withdrawalAmount = depositAmount + SilverBankAccount.FEE;
        getAccount().deposit(depositAmount);
        assertThrows(IllegalStateException.class, () -> getAccount().withdraw(withdrawalAmount));
    }

}
