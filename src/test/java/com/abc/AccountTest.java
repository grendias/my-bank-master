package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test account type
    public void accountType() {
        Account savingsAccount = new Account(Account.SAVINGS);

        assertEquals(1, savingsAccount.getAccountType());
    }

    @Test //Test account deposit with amount > 0
    public void accountDeposit() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(2000.0);

        assertEquals(2000, savingsAccount.sumTransactions(), DOUBLE_DELTA);
    }

    //Test account deposit with amount <= 0
    @Test  (expected = IllegalArgumentException.class)
    public void accountDepositNegative() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(0);

        assertEquals("amount must be greater than zero", savingsAccount.sumTransactions());
    }

    @Test //Test account withdraw with amount > 0
    public void accountWithdraw() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(2000.0);
        savingsAccount.withdraw(1000.0);

        assertEquals(1000, savingsAccount.sumTransactions(), DOUBLE_DELTA);
    }

    //Test account withdraw with amount <= 0
    @Test  (expected = IllegalArgumentException.class)
    public void accountWithdrawNegative() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(100.0);
        savingsAccount.withdraw(-100.0);

        assertEquals("amount must be greater than zero", savingsAccount.sumTransactions());
    }

    @Test //Test account interest earned on checking account with no transactions
    public void accountNoTransactionInterest() {
        Account checkingAccount = new Account(Account.CHECKING);

        assertEquals(0.0, checkingAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on checking account
    public void accountCheckingInterest() {
        Account checkingAccount = new Account(Account.CHECKING);
        checkingAccount.deposit(500.0);

        assertEquals(0.5, checkingAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on savings account with up to 1000 money
    public void accountSavingsInterest1000() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(1000.0);

        assertEquals(1.0, savingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on savings account with more than 1000 money
    public void accountSavingsInterest2000() {
        Account savingsAccount = new Account(Account.SAVINGS);
        savingsAccount.deposit(2000.0);

        assertEquals(3.0, savingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on maxi-savings account with up to 1000 money
    public void accountMaxiSavingsInterest1000() {
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        maxiSavingsAccount.deposit(1000.0);

        assertEquals(50.0, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on maxi-savings account with above 1000 and upto 2000 money
    public void accountMaxiSavingsInterest2000() {
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        maxiSavingsAccount.deposit(2000.0);

        assertEquals(100.0, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on maxi-savings account with above 2000 money
    public void accountMaxiSavingsInterest3000() {
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        maxiSavingsAccount.deposit(3000.0);

        assertEquals(150.0, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }

    @Test //Test account interest earned on maxi-savings account with money (1000, 2000) and withdrawal in past 10 days
    public void accountMaxiSavingsInterestTenDaysWithdraw() {
        Account maxiSavingsAccount = new Account(Account.MAXI_SAVINGS);
        maxiSavingsAccount.deposit(1500.0);
        maxiSavingsAccount.withdraw(100.0);
        assertEquals(1.4, maxiSavingsAccount.interestEarned(), DOUBLE_DELTA);
    }
}