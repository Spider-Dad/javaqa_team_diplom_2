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
    void creatingCreditAccountWithValidParametersShouldSucceed() {
        // Arrange
        int initialBalance = 10000;
        int creditLimit = 1000;
        int rate = 10;

        // Act
        CreditAccount creditAccount = new CreditAccount(initialBalance, creditLimit, rate);

        // Assert
        assertEquals(initialBalance, creditAccount.getBalance());
        assertEquals(creditLimit, creditAccount.getCreditLimit());
        assertEquals(rate, creditAccount.getRate());
    }

    @Test
    void creatingCreditAccountWithNegativeCreditLimitShouldThrowException() {

        int initialBalance = 0;
        int creditLimit = -1000;
        int rate = 10;

           assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, rate);
        });
    }

    @Test
    void creatingCreditAccountWithZeroRateShouldThrowException() {
        // Arrange
        int initialBalance = 0;
        int creditLimit = 1000;
        int zeroRate = 0;
        int negativeRate = -5;

        // Act & Assert
        assertAll("creatingCreditAccountWithZeroOrNegativeRateShouldThrowException",
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new CreditAccount(initialBalance, creditLimit, zeroRate);
                }, "Zero rate should throw IllegalArgumentException."),
                () -> assertThrows(IllegalArgumentException.class, () -> {
                    new CreditAccount(initialBalance, creditLimit, negativeRate);
                }, "Negative rate should throw IllegalArgumentException.")
        );
    }

    @Test
    void initialBalanceShouldBeCorrect() {
        // Arrange
        int initialBalance = 10000;
        int creditLimit = 1000;
        int rate = 10;
        CreditAccount creditAccount = new CreditAccount(initialBalance, creditLimit, rate);

        // Act & Assert
        assertEquals(initialBalance, creditAccount.getBalance());
    }


    @Test
    public void testValidPayment() {
        int initialBalance = 10_000;
        int creditLimit = 15_000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        boolean result = account.pay(1000);
        assertTrue(result);
        assertEquals(9000, account.getBalance());
    }


    @Test
    public void testNegativePaymentAmount() {
        int initialBalance = 10_000;
        int creditLimit = 15_000;
        int amount = -1000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        boolean result = account.pay(-1000);
        assertFalse(result);
        assertEquals(10000, account.getBalance());
    }


   
    @Test
    public void testPauTheBillInsufficientFunds() {
        int initialBalance = 10_000;
        int creditLimit = 1000;
        int amount = 11000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);


        boolean result = account.pay(11000);
        assertFalse(result);
        assertEquals(10000, account.getBalance());

    }

    @Test
    public void testPayTheBillNegativeBalance() {
        int initialBalance = -10_000;
        int creditLimit = 1000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);

        boolean result = account.pay(200);
        assertFalse(result);
        assertEquals(-10000, account.getBalance());
    }


    @Test
    public void testAddWithValidAmount() {
        int initialBalance = 10_000;
        int creditLimit = 1000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        assertTrue(account.add(2000));
        assertEquals(12000, account.getBalance());
    }

    @Test
    public void testAddWithNegativeAmount() {
        int initialBalance = 10_000;
        int creditLimit = 1000;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        assertFalse(account.add(-2000));
        assertEquals(10000, account.getBalance());
    }

    @Test
    public void testAddWithZeroAmount() {
        int initialBalance = 10_000;
        int creditLimit = 1000;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        assertFalse(account.add(0));
        assertEquals(10000, account.getBalance());
    }
    @Test
    public void testYearChangeNegativeBalance() {
        CreditAccount account = new CreditAccount(-200, 15, 0);
        assertEquals(-30, account.yearChange());

           }
    @Test
    public void testYearChangePositiveBalance() {
        CreditAccount account = new CreditAccount(200, 15, 0);
        assertEquals(0, account.yearChange());
    }



}





