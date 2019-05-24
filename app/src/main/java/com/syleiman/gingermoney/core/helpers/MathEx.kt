package com.syleiman.gingermoney.core.helpers

/**
 * Math extra functions
 */
object MathEx {
    /**
     * Returns 10 in power [power]
     */
    fun pow10(power: Int): Long {
        var result = 1L

        repeat(power) {
            result *= 10
        }

        return result
    }
}