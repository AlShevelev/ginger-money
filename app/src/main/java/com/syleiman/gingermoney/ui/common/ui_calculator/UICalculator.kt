package com.syleiman.gingermoney.ui.common.ui_calculator

import android.content.Context
import javax.inject.Inject

/**
 * Some applied calculations for UI
 */
class UICalculator
@Inject
constructor(
    private val context: Context
) : UICalculatorInterface {

    /**
     * Converts Dp value to pixels
     */
    override fun dpToPixels(dpValue: Float): Int = Math.round(dpValue * getAverageDensity())

    /**
     * Converts pixel value to Dp
     */
    override fun pixelsToDp(pixelValue: Int): Float = pixelValue / getAverageDensity()

    private fun getAverageDensity(): Float = context.resources.displayMetrics.density
}