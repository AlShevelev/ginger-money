package com.syleiman.gingermoney

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.widgets.amount_keyboard.AmountKeyboardMoney
import org.junit.Test

class AmountKeyboardMoneyExceptionsTest {
    @Test(expected = IllegalArgumentException::class)
    fun testMainValueOverflow() {
        // Arrange
        AmountKeyboardMoney(Currency.USD.toMoney(11111111111111111.11))
    }
}