package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.*
import org.junit.Assert
import org.junit.Test

class ExchangeRateMatrixTest {
    /**
     * Check quotes for the same currency
     */
    @Test
    fun checkMatrixDiagonal() {
        // Arrange
        val matrix = createTestMatrix()

        // Act
        val usdRate = matrix.getExchangeRate(Currency.USD, Currency.USD)
        val eurRate = matrix.getExchangeRate(Currency.EUR, Currency.EUR)
        val rubRate = matrix.getExchangeRate(Currency.RUB, Currency.RUB)

        // Assert
        Assert.assertEquals(1.0, usdRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.USD, usdRate.from)
        Assert.assertEquals(Currency.USD, usdRate.to)

        Assert.assertEquals(1.0, eurRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.EUR, eurRate.from)
        Assert.assertEquals(Currency.EUR, eurRate.to)

        Assert.assertEquals(1.0, rubRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.RUB, rubRate.from)
        Assert.assertEquals(Currency.RUB, rubRate.to)
    }

    /**
     * Check direct rates
     */
    @Test
    fun checkMatrixDirectRates() {
        // Arrange
        val matrix = createTestMatrix()

        // Act
        val eurToUsdRate = matrix.getExchangeRate(Currency.EUR, Currency.USD)
        val usdToEurRate = matrix.getExchangeRate(Currency.USD, Currency.EUR)
        val rubToUsdRate = matrix.getExchangeRate(Currency.RUB, Currency.USD)
        val usdToRubRate = matrix.getExchangeRate(Currency.USD, Currency.RUB)

        // Assert
        Assert.assertEquals(0.874, eurToUsdRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.EUR, eurToUsdRate.from)
        Assert.assertEquals(Currency.USD, eurToUsdRate.to)

        Assert.assertEquals(1.144, usdToEurRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.USD, usdToEurRate.from)
        Assert.assertEquals(Currency.EUR, usdToEurRate.to)

        Assert.assertEquals(66.666, rubToUsdRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.RUB, rubToUsdRate.from)
        Assert.assertEquals(Currency.USD, rubToUsdRate.to)

        Assert.assertEquals(0.015, usdToRubRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.USD, usdToRubRate.from)
        Assert.assertEquals(Currency.RUB, usdToRubRate.to)
    }

    /**
     * Check relative rates
     */
    @Test
    fun checkMatrixRelativeRates() {
        // Arrange
        val matrix = createTestMatrix()

        // Act
        val eurToRubRate = matrix.getExchangeRate(Currency.EUR, Currency.RUB)
        val rubToEurRate = matrix.getExchangeRate(Currency.RUB, Currency.EUR)

        // Assert
        Assert.assertEquals(0.013, eurToRubRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.EUR, eurToRubRate.from)
        Assert.assertEquals(Currency.RUB, eurToRubRate.to)

        Assert.assertEquals(76.277, rubToEurRate.quoteFactor, 0.001)
        Assert.assertEquals(Currency.RUB, rubToEurRate.from)
        Assert.assertEquals(Currency.EUR, rubToEurRate.to)
    }

    /**
     * Try to convert money via the matrix
     */
    @Test
    fun checkMatrixConversion() {
        // Arrange
        val matrix = createTestMatrix()
        val moneyUsd = Currency.USD.toMoney(1.0)     // 1$

        // Act
        val usdToRubRate = matrix.getExchangeRate(Currency.USD, Currency.RUB)
        val moneyRub = moneyUsd.convertTo(usdToRubRate)

        // Assert
        Assert.assertEquals(Currency.RUB, moneyRub.currency)
        Assert.assertEquals(66.67, moneyRub.value, 0.001)
    }

    /**
     * Returns valid matrix
     */
    private fun createTestMatrix() : ExchangeRateMatrix =
        ExchangeRateMatrix(
            listOf(
                ExchangeRate(Currency.EUR, Currency.USD, 0.874),
                ExchangeRate(Currency.RUB, Currency.USD, 66.666)))
}