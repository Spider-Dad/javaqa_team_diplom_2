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
    public void shouldNotChangeBalanceWhenAddingNegativeAmount() { //
        CreditAccount account = new CreditAccount(
                10,
                1_000,
                20
        );
        account.add(-10);
        assertEquals(10, account.getBalance());


    }

    @Test
    public void shouldAddAtHugeBalance() { //
        CreditAccount account = new CreditAccount(
                1_000_000,
                15_000,
                10
        );
        account.add(15);
        assertEquals(1_000_015, account.getBalance());

    }

    @Test
    void creatingCreditAccountWithValidParametersShouldSucceed() {  // проверка создания конструктора с корректными параметрами
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
    void creatingCreditAccountWithNegativeCreditLimitShouldThrowException() { // Нет баг-репорта. проверка на выброс исключения при создании конструктора с отрицательным кредитным лимитом
        // Arrange
        int initialBalance = 0;
        int creditLimit = -1000;
        int rate = 10;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, rate);
        });
    }

    @Test
    void creatingCreditAccountWithZeroRateShouldThrowException() { // проверка на выброс исключения при создании конструктора со ставкой <  0
        // Arrange
        int initialBalance = 0;
        int creditLimit = 1000;
        int negativeRate = -5;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(initialBalance, creditLimit, negativeRate);
        });

    }

    @Test
    public void testValidPayment() { // fix #8
        int initialBalance = 10_000;
        int creditLimit = 15_000;
        int amount = 1000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        boolean result = account.pay(amount);
        assertTrue(result);
        assertEquals(9000, account.getBalance());
    }


    @Test
    public void testNegativePaymentAmount() { // проверка на отрицателное значение amount
        int initialBalance = 10_000;
        int creditLimit = 15_000;
        int amount = -1000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        boolean result = account.pay(amount);
        assertFalse(result);
        assertEquals(10000, account.getBalance());
    }


    @Test
    public void testCreditLimitExceeded() { // issue #10
        int initialBalance = 10_000;
        int creditLimit = 15_000;
        int amount = 25_000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);

        assertThrows(IllegalStateException.class, () -> account.pay(amount));
        assertEquals(10_000, account.getBalance());
    }

    @Test
    public void testPauTheBillInsufficientFunds() { // issue #11
        int initialBalance = 10_000;
        int creditLimit = 1_000;
        int amount = 11_000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);

        assertThrows(IllegalStateException.class, () -> account.pay(amount));
        assertEquals(10_000, account.getBalance());

    }

    @Test
    public void testPayTheBillNegativeBalance() { // issue #12
        assertThrows(IllegalArgumentException.class, () -> {
            int initialBalance = -10_000;
            int creditLimit = 1000;

            new CreditAccount(initialBalance, creditLimit, 10);
        });

    }


    @Test
    public void testAddWithValidAmount() { // fix #9
        int initialBalance = 10_000;
        int creditLimit = 1_000;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        assertTrue(account.add(2_000));
        assertEquals(12_000, account.getBalance());
    }


    @Test
    public void testAddWithZeroAmount() {
        int initialBalance = 10_000;
        int creditLimit = 1_000;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, 10);
        assertFalse(account.add(0));
        assertEquals(10_000, account.getBalance());
    }

    @Test
    public void testYearChange() {
        CreditAccount account = new CreditAccount(200, 15, 0);

        assertEquals(0, account.yearChange());

        account = new CreditAccount(0, 300, 15);
        account.pay(200); // Покупка для отрицательного баланса

        assertEquals(30, account.yearChange());
    }
//    @Test
//    public void testGetCreditLimit() {
//        CreditAccount account = new CreditAccount(-200, 15, 0);
//        assertEquals(0, account.getCreditLimit());
//
//        account = new CreditAccount(200, 15, 100);
//        assertEquals(100, account.getCreditLimit());
//    }

}





