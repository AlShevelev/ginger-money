package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.IncorrectMoneyOperationException
import com.syleiman.gingermoney.core.global_entities.money.toExchangeRate
import com.syleiman.gingermoney.core.global_entities.money.toMoney
import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyTest {

    /**
     *
     */
    @Test
    fun createUSDFromCents() {
        // Act
        val money = Currency.USD.toMoney(513)

        // Assert
        assertEquals(Currency.USD, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createUSDFromValue() {
        // Act
        val money = Currency.USD.toMoney(5.13)

        // Assert
        assertEquals(Currency.USD, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createEURFromCents() {
        // Act
        val money = Currency.EUR.toMoney(513)

        // Assert
        assertEquals(Currency.EUR, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createEURFromValue() {
        // Act
        val money = Currency.EUR.toMoney(5.13)

        // Assert
        assertEquals(Currency.EUR, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createRUBFromCents() {
        // Act
        val money = Currency.RUB.toMoney(513)

        // Assert
        assertEquals(Currency.RUB, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createRUBFromValue() {
        // Act
        val money = Currency.RUB.toMoney(5.13)

        // Assert
        assertEquals(Currency.RUB, money.currency)
        assertEquals(5.13, money.value, 0.001)
        assertEquals(100, money.centsFactor)
    }

    /**
     *
     */
    @Test
    fun createFromZeroCents() {
        // Act
        val money = Currency.USD.toMoney(0)

        // Assert
        assertEquals(0.00, money.value, 0.001)
    }

    /**
     *
     */
    @Test
    fun createFromZeroValue() {
        // Act
        val money = Currency.USD.toMoney(0.00)

        // Assert
        assertEquals(0.00, money.value, 0.001)
    }

    /**
     *
     */
    @Test
    fun createFromNegativeCents() {
        // Act
        val money = Currency.USD.toMoney(-50)

        // Assert
        assertEquals(-0.5, money.value, 0.001)
    }

    /**
     *
     */
    @Test
    fun createFromNegativeValue() {
        // Act
        val money = Currency.USD.toMoney(-0.5)

        // Assert
        assertEquals(-0.5, money.value, 0.001)
    }

    /**
     *
     */
    @Test
    fun add() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val money2 = Currency.USD.toMoney(1.291)

        // Act
        val result = money1 + money2

        // Assert
        assertEquals(Currency.USD.toMoney(2.61), result)
    }

    /**
     *
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun addFail() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val money2 = Currency.EUR.toMoney(1.291)

        // Act
        money1 + money2
    }

    /**
     *
     */
    @Test
    fun subtract() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val money2 = Currency.USD.toMoney(1.291)

        // Act
        val result = money1 - money2

        // Assert
        assertEquals(Currency.USD.toMoney(0.03), result)
    }

    /**
     *
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun subtractFail() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val money2 = Currency.EUR.toMoney(1.291)

        // Act
        money1 - money2
    }

    /**
     *
     */
    @Test
    fun compareToEquals() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.31)
        val money2 = Currency.USD.toMoney(1.31)

        // Act
        val result = money1 == money2

        // Assert
        assertEquals(true, result)
    }

    /**
     *
     */
    @Test
    fun compareToGreater() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.5)
        val money2 = Currency.USD.toMoney(1.31)

        // Act
        val result = money1 > money2

        // Assert
        assertEquals(true, result)
    }

    /**
     *
     */
    @Test
    fun compareToLess() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.31)
        val money2 = Currency.USD.toMoney(1.5)

        // Act
        val result = money1 < money2

        // Assert
        assertEquals(true, result)
    }

    /**
     *
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun compareToFail() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val money2 = Currency.EUR.toMoney(1.313)

        // Act
        money1.compareTo(money2)
    }

    /**
     * Source currencies are not the same (USD & EUR)
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun convertToFailInvalidSourceCurrencies() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val rate = Currency.EUR.toExchangeRate(Currency.USD, 5.0)

        // Act
        money1.convertTo(rate)
    }

    /**
     * Invalid exchange rate - "from" currency as same as "to"
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun convertToFailInvalidExchangeRateSameCurrency() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val rate = Currency.USD.toExchangeRate(Currency.USD, 5.0)

        // Act
        money1.convertTo(rate)
    }

    /**
     * Invalid exchange rate - quotation rate is negative
     */
    @Test(expected = IncorrectMoneyOperationException::class)
    fun convertToFailInvalidExchangeRateNegative() {
        // Arrange
        val money1 = Currency.USD.toMoney(1.316)
        val rate = Currency.USD.toExchangeRate(Currency.EUR, -5.0)

        // Act
        money1.convertTo(rate)
    }

    /**
     *
     */
    @Test
    fun convertTo() {
        // Arrange
        val money1 = Currency.USD.toMoney(5.0)
        val rate = Currency.USD.toExchangeRate(Currency.EUR, 5.0)

        // Act
        val result = money1.convertTo(rate)

        // Assert
        assertEquals(Currency.EUR, result.currency)
        assertEquals(1.0, result.value, 0.001)
    }
}