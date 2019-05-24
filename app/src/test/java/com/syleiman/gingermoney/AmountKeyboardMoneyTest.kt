package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardMoney
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class AmountKeyboardMoneyTest(private val sourceMoney: Money, private val tag: String) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() : Collection<Array<Any>> {
            return listOf(
                arrayOf(Currency.USD.toMoney(0),  "zeroCase1"),
                arrayOf(Currency.USD.toMoney(0.9),  "zeroCase2"),
                arrayOf(Currency.USD.toMoney(0.09),  "zeroCase3"),
                arrayOf(Currency.USD.toMoney(0.99),  "zeroCase4"),
                arrayOf(Currency.USD.toMoney(1),  "oneCase1"),
                arrayOf(Currency.USD.toMoney(1.99),  "oneCase2"),
                arrayOf(Currency.USD.toMoney(10.10),  "tenCase1"),

                arrayOf(Currency.USD.toMoney(-0.9),  "zeroCase2Negative"),
                arrayOf(Currency.USD.toMoney(-0.09),  "zeroCase3Negative"),
                arrayOf(Currency.USD.toMoney(-0.99),  "zeroCase4Negative"),
                arrayOf(Currency.USD.toMoney(-1),  "oneCase1Negative"),
                arrayOf(Currency.USD.toMoney(-1.99),  "oneCase2Negative"),
                arrayOf(Currency.USD.toMoney(-10.10),  "tenCase1Negative"),

                arrayOf(Currency.USD.toMoney(11111111111111.11),  "maxValue")
            )
        }
    }

    @Test
    fun test() {
        // Arrange
        val testSubject = AmountKeyboardMoney(sourceMoney)

        // Act
        val resultMoney = testSubject.toMoney()

        // Assert
        assertEquals("[$tag] test fail!", sourceMoney, resultMoney)
    }
}