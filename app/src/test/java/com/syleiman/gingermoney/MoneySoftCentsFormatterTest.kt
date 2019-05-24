package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.formatters.MoneySoftCentsFormatter
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class MoneySoftCentsFormatterTest(private val sourceMoney: Money, private val expectedString: String, private val tag: String) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                arrayOf(Currency.USD.toMoney(0.00), "0", "zeroCase1"),
                arrayOf(Currency.USD.toMoney(-0.00), "0", "zeroCase1Negative"),
                arrayOf(Currency.USD.toMoney(0.4), "0.4", "zeroCase2"),
                arrayOf(Currency.USD.toMoney(-0.4), "-0.4", "zeroCase2Negative"),
                arrayOf(Currency.USD.toMoney(0.02), "0.02", "zeroCase3"),
                arrayOf(Currency.USD.toMoney(-0.02), "-0.02", "zeroCase3Negative"),
                arrayOf(Currency.USD.toMoney(0.42), "0.42", "zeroCase4"),
                arrayOf(Currency.USD.toMoney(-0.42), "-0.42", "zeroCase4Negative"),

                arrayOf(Currency.USD.toMoney(1.00), "1", "oneCase1"),
                arrayOf(Currency.USD.toMoney(-1.00), "-1", "oneCase1Negative"),
                arrayOf(Currency.USD.toMoney(1.4), "1.4", "oneCase2"),
                arrayOf(Currency.USD.toMoney(-1.4), "-1.4", "oneCase2Negative"),
                arrayOf(Currency.USD.toMoney(1.02), "1.02", "oneCase3"),
                arrayOf(Currency.USD.toMoney(-1.02), "-1.02", "oneCase3Negative"),
                arrayOf(Currency.USD.toMoney(1.42), "1.42", "oneCase4"),
                arrayOf(Currency.USD.toMoney(-1.42), "-1.42", "oneCase4Negative"),

                arrayOf(Currency.USD.toMoney(10.00), "10", "ten"),
                arrayOf(Currency.USD.toMoney(-10.00), "-10", "tenNegative"),
                arrayOf(Currency.USD.toMoney(100.00), "100", "oneHundred"),
                arrayOf(Currency.USD.toMoney(-100.00), "-100", "oneHundredNegative"),
                arrayOf(Currency.USD.toMoney(1000.00), "1,000", "oneThousand"),
                arrayOf(Currency.USD.toMoney(-1000.00), "-1,000", "oneThousandNegative"),
                arrayOf(Currency.USD.toMoney(1000000.00), "1,000,000", "oneMillion"),
                arrayOf(Currency.USD.toMoney(-1000000.00), "-1,000,000", "oneMillionNegative")
            )
        }
    }

    @Test
    fun test() {
        // Arrange
        val formatter = MoneySoftCentsFormatter()

        // Act
        val stringValue = formatter.format(sourceMoney)

        // Assert
        assertEquals("[$tag] test fail!", "${sourceMoney.currency.symbol} $expectedString", stringValue)
    }
}