package it.unibo.pps.e1;

import it.unibo.pps.e1.api.BankAccount;
import it.unibo.pps.e1.impl.CoreBankAccount;
import it.unibo.pps.e1.impl.SilverBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SilverBankAccountTest {

    private final int FIRST_DEPOSIT_AMOUNT = 1000;

    private BankAccount account;

    @BeforeEach
    void init(){
        this.account = new SilverBankAccount(new CoreBankAccount());
    }

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
    public void testCanWithdraw() {
        final int withdrawalAmount = 200;
        final int expectedRemainingAmount = FIRST_DEPOSIT_AMOUNT - (withdrawalAmount + SilverBankAccount.FEE);
        this.account.deposit(FIRST_DEPOSIT_AMOUNT);
        this.account.withdraw(withdrawalAmount);
        assertEquals(expectedRemainingAmount, this.account.getBalance());
    }

    @Test
    public void testCannotWithdrawMoreThanAvailable(){
        final int withdrawalAmount = 1200;
        this.account.deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(withdrawalAmount));
    }

    @Test
    public void testWithdrawalCannotExceedWithFee() {
        final int withdrawalAmount = FIRST_DEPOSIT_AMOUNT + SilverBankAccount.FEE;
        this.account.deposit(FIRST_DEPOSIT_AMOUNT);
        assertThrows(IllegalStateException.class, () -> this.account.withdraw(withdrawalAmount));
    }

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
