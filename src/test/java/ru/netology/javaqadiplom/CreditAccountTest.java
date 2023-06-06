package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldAddToNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -10,
                1_000,
                20
        );
        account.add(-10);
        assertEquals(-10, account.getBalance());


    }

    @Test
    public void shouldAddAtHugeBalance() {
        CreditAccount account = new CreditAccount(
                1_000_000,
                15_000,
                10
        );
        account.add(15);
        assertEquals(15, account.getBalance());

    }

    @Test
    public void ifCreditRateIsOne() {
        CreditAccount account = new CreditAccount(
                1_000_000,
                15_000,
                1
        );
        account.add(1);
        assertEquals(1, account.getBalance());

    }

    @Test
    public void ifCreditRateIs99() {
        CreditAccount account = new CreditAccount(
                1_000_000,
                15_000,
                99
        );
        account.add(1);
        assertEquals(1, account.getBalance());
    }


    @Test
    public void testPayTheBillPositiveBalance() {

        int initialBalance = 1_000_000;
        int creditLimit = 1000;
        int amount = 200;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);


        boolean result = account.pay(amount);


        assertTrue(result);
        assertEquals(initialBalance - amount, account.getBalance());


    }

    @Test
    public void testPayTheBillZeroBalance() {

        int initialBalance = 0;
        int creditLimit = 1000;
        int amount = 200;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);


        boolean result = account.pay(amount);


        assertTrue(result);
        assertEquals(initialBalance - amount, account.getBalance());
    }


    @Test
    public void testPayTheBillExceedingBalance() {

        int initialBalance = 10_000;
        int creditLimit = 1000;
        int amount = 1200;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);

        boolean result = account.pay(amount);

        assertFalse(result);
        assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void testPayTheBillNegativeAmount() {
        int initialBalance = 10_000;
        int creditLimit = 1000;
        int amount = -200;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);


        boolean result = account.pay(amount);


        assertFalse(result);
        assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void testPayTheBillNegativeBalance() {
        int initialBalance = -10_000;
        int creditLimit = 1000;
        int amount = 200;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);


        boolean result = account.pay(amount);


        assertFalse(result);
        assertEquals(initialBalance, account.getBalance());
    }

    @Test
    public void testAddFundsWithValidAmount() {
        int initialBalance = 100_000;
        int creditLimit = 15_000;
        int amountToAdd = 50;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        account.setBalance(initialBalance);
        boolean success = account.addFunds(amountToAdd);
        assertTrue(success);
        assertEquals(initialBalance + amountToAdd, account.getBalance(), 0.001);
    }

    @Test
    public void testAddFundsWithNegativeAmount() {
        int initialBalance = 100_000;
        int creditLimit = 15_000;
        int amountToAdd = -50;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        account.setBalance(initialBalance);
        boolean success = account.addFunds(amountToAdd);
        assertFalse(success);
        assertEquals(initialBalance + amountToAdd, account.getBalance(), 0.001);
    }

    @Test
    public void testAddFundsExceedLimit() {
        int initialBalance = 100_000;
        int creditLimit = 15_000;
        int amountToAdd = 110_000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        account.setBalance(initialBalance);
        boolean success = account.addFunds(amountToAdd);
        assertFalse(success);
        assertEquals(initialBalance + amountToAdd, account.getBalance(), 0.001);
    }

    @Test
    public void testNegativeBalanceItsRate() {
        int initialBalance = -200;
        int creditLimit = 15_000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 15);
        int expectedInterest = -30;
        assertEquals(expectedInterest, account.calculateYearlyInterest(rate));
    }

    @Test
    public void testPositiveBalanceItsRate() {
        int initialBalance = 200;
        int creditLimit = 15_000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 15);
        int expectedInterest = 0;
        assertEquals(expectedInterest, account.calculateYearlyInterest(rate));
    }

}





