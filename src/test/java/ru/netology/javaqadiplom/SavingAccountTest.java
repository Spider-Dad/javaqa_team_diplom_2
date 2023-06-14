package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void shouldAddEqualsMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    @Test
    public void shouldNotAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                4_000,
                5
        );

        boolean result = account.add(3_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNoAddZeroTest() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.add(0);
        Assertions.assertFalse(result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldNoAddLessZeroTest() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.add(-5);
        Assertions.assertFalse(result);
        Assertions.assertEquals(5_000, account.getBalance());
    }

    @Test
    public void shouldPayEqualsMinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.pay(4_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(5_000 - 4_000, account.getBalance());
    }

    @Test
    public void shouldPayWithinBalance() {
        SavingAccount account = new SavingAccount(
                5_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.pay(3_000);

        Assertions.assertTrue(result);
        Assertions.assertEquals(5_000 - 3_000, account.getBalance());
    }

    @Test
    public void shouldNotPayBeyondBalance() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.pay(3_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldNotPayIfBalanceSame() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                2_000,
                5
        );

        boolean result = account.pay(2_000);

        Assertions.assertFalse(result);
        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void payBalanceLessThanMinBalanceIsFalse() {
        SavingAccount account = new SavingAccount(
                100,
                50,
                100,
                5
        );

        boolean result = account.pay(60);

        Assertions.assertFalse(result);
        Assertions.assertEquals(100, account.getBalance());
    }

    @Test
    public void shouldNotPayZeroAmount() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );

        boolean result = account.pay(0);

        Assertions.assertFalse(result);
        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldCalculateYearChange() {
        SavingAccount account = new SavingAccount(1000, 0, 2000, 10); // Создание экземпляра с известными параметрами
        int expectedInterest = 1000 / 100 * 10; // Расчет ожидаемых процентов
        int actualInterest = account.yearChange(); // Вызов метода yearChange()
        assert expectedInterest == actualInterest : "Некорректный расчет процентов"; // Сравнение ожидаемого и фактического результатов
    }

    @Test
    public void testIllegalArgumentExceptionInvalidRate() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(5_000, 1_000, 10_000, -5));
    }

    @Test
    public void testIllegalArgumentExceptionMinBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(5_000, 2_000, 1_000, 5));
    }

    @Test
    public void testIllegalArgumentExceptionInitialBalanceLessMinBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(500, 1_000, 10_000, 5));
    }

    @Test
    public void testIllegalArgumentExceptionInitialBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SavingAccount(15_000, 1_000, 10_000, 5));
    }
}



