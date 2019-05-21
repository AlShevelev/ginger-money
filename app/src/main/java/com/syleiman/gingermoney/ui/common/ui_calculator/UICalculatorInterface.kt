package com.syleiman.gingermoney.ui.common.ui_calculator

/**
 * Some applied calculations for UI
 */
interface UICalculatorInterface {
    /**
     * Converts Dp value to pixels
     */
    fun dpToPixels(dpValue: Float): Int

    /**
     * Converts pixel value to Dp
     */
    fun pixelsToDp(pixelValue: Int): Float
}