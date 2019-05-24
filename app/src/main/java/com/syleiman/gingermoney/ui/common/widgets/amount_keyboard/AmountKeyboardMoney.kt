package com.syleiman.gingermoney.ui.common.widgets.amount_keyboard

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.helpers.MathEx
import java.util.*

/**
 * Internal money representation for account keyboard
 */
class AmountKeyboardMoney(source: Money) {
    private val mainValueDigits: MutableList<Byte>
    private val centsDigits: MutableList<Byte>

    private var isPositive = source.value >= 0

    private val maxCentDigits = Math.log10(source.centsFactor.toDouble()).toInt()
    private val maxMainValueDigits = 14

    var hasCents: Boolean
    private set

    val currency: Currency = source.currency

    init {
        val totalCents = Math.abs(source.totalCents)

        val mainValue = totalCents / source.centsFactor
        val cents = totalCents % source.centsFactor

        mainValueDigits = splitToDigits(mainValue)
        if(mainValueDigits.size > maxMainValueDigits) {
            throw IllegalArgumentException("Value is too large: ${source.value}")
        }

        centsDigits = splitToDigits(cents)
        if(centsDigits.size > maxCentDigits) {
            throw IllegalArgumentException("Value is too large: ${source.value}")
        }

        hasCents = centsDigits.isNotEmpty()
    }

    fun toMoney(): Money {
        var totalCents = joinDigits(mainValueDigits) * currency.centsFactor + joinDigits(centsDigits)

        if(!isPositive) {
            totalCents *= -1
        }

        return currency.toMoney(totalCents)
    }

    fun changeSign() {
        isPositive = !isPositive
    }

    fun processDigit(keyCode: AmountKeyboardKeyCode) {
        val digit = getDigitFromKeyCode(keyCode)

        if(hasCents) {
            if(centsDigits.size < maxCentDigits) {
                centsDigits.add(digit)
            } else {
                centsDigits[centsDigits.lastIndex] = digit
            }
        } else {
            if(mainValueDigits.size < maxMainValueDigits) {
                if(mainValueDigits.isEmpty() && digit == 0.toByte()) {
                    return
                }

                mainValueDigits.add(digit)
            } else {
                mainValueDigits[mainValueDigits.lastIndex] = digit
            }
        }
    }

    fun processDot() {
        if(hasCents) {
            return
        }

        if(mainValueDigits.isEmpty()) {
            mainValueDigits.add(0)
        }

        hasCents = true
    }

    fun processBackspace() {
        if(centsDigits.isNotEmpty()) {
            centsDigits.removeAt(centsDigits.lastIndex)
        } else {
            if (mainValueDigits.isNotEmpty()) {
                mainValueDigits.removeAt(mainValueDigits.lastIndex)
            }
        }

        hasCents = centsDigits.isNotEmpty()
    }

    /**
     * Splits Long value to a set of digits
     */
    private fun splitToDigits(sourceValue: Long): MutableList<Byte> {
        var value = sourceValue

        // Splitting
        val digits = Stack<Byte>()
        while (value != 0L) {
            digits.push((value % 10).toByte())
            value /= 10
        }

        // Inversion
        val result = mutableListOf<Byte>()
        while(digits.isNotEmpty()) {
            result.add(digits.pop())
        }

        return result
    }

    /**
     * Join a set of digits into one value
     */
    private fun joinDigits(sourceDigits: List<Byte>): Long {
        var result = 0L

        for(i in 0 until sourceDigits.size) {
            result += sourceDigits[i] * MathEx.pow10(sourceDigits.size - 1 - i)
        }

        return result
    }

    private fun getDigitFromKeyCode(keyCode: AmountKeyboardKeyCode): Byte =
        when(keyCode) {
            AmountKeyboardKeyCode.KEY_0 -> 0
            AmountKeyboardKeyCode.KEY_1 -> 1
            AmountKeyboardKeyCode.KEY_2 -> 2
            AmountKeyboardKeyCode.KEY_3 -> 3
            AmountKeyboardKeyCode.KEY_4 -> 4
            AmountKeyboardKeyCode.KEY_5 -> 5
            AmountKeyboardKeyCode.KEY_6 -> 6
            AmountKeyboardKeyCode.KEY_7 -> 7
            AmountKeyboardKeyCode.KEY_8 -> 8
            AmountKeyboardKeyCode.KEY_9 -> 9
            else -> throw java.lang.IllegalArgumentException("This key is not a digit key: $keyCode")
        }
}