package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.formatters.MoneyHardCentsFormatter
import org.junit.Assert.assertEquals
import org.junit.Test

class MoneyHardCentsFormatterTest {
    @Test
    fun zeroCase1() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(0.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 0.00", stringValue)
    }

    @Test
    fun zeroCase1Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-0.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 0.00", stringValue)
    }

    @Test
    fun zeroCase2() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(0.4)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 0.40", stringValue)
    }

    @Test
    fun zeroCase2Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-0.4)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -0.40", stringValue)
    }

    @Test
    fun zeroCase3() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(0.02)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 0.02", stringValue)
    }

    @Test
    fun zeroCase3Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-0.02)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -0.02", stringValue)
    }

    @Test
    fun zeroCase4() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(0.42)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 0.42", stringValue)
    }

    @Test
    fun zeroCase4Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-0.42)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -0.42", stringValue)
    }

    @Test
    fun oneCase1() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1.00", stringValue)
    }

    @Test
    fun oneCase1Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1.00", stringValue)
    }

    @Test
    fun oneCase2() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1.4)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1.40", stringValue)
    }

    @Test
    fun oneCase2Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1.4)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1.40", stringValue)
    }

    @Test
    fun oneCase3() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1.02)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1.02", stringValue)
    }

    @Test
    fun oneCase3Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1.02)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1.02", stringValue)
    }

    @Test
    fun oneCase4() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1.42)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1.42", stringValue)
    }

    @Test
    fun oneCase4Negative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1.42)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1.42", stringValue)
    }

    @Test
    fun ten() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(10.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 10.00", stringValue)
    }

    @Test
    fun tenNegative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-10.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -10.00", stringValue)
    }

    @Test
    fun oneHundred() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(100.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 100.00", stringValue)
    }

    @Test
    fun oneHundredNegative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-100.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -100.00", stringValue)
    }

    @Test
    fun oneThousand() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1000.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1,000.00", stringValue)
    }

    @Test
    fun oneThousandNegative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1000.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1,000.00", stringValue)
    }

    @Test
    fun oneMillion() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(1000000.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} 1,000,000.00", stringValue)
    }

    @Test
    fun oneMillionNegative() {
        // Arrange
        val moneyValue = Currency.USD.toMoney(-1000000.00)
        val formatter = MoneyHardCentsFormatter()

        // Act
        val stringValue = formatter.format(moneyValue)

        // Assert
        assertEquals("${moneyValue.currency.symbol} -1,000,000.00", stringValue)
    }
}