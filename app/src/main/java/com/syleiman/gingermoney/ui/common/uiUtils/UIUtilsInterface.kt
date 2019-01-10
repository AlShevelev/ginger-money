package com.syleiman.gingermoney.ui.common.uiUtils

import android.content.Context
import androidx.annotation.StringRes

/**
 * Common UI helper
 */
interface UIUtilsInterface {
    /**
     * Show non-blocking UI error message
     */
    fun showError(context: Context, @StringRes messageResId: Int)

    /**
     * Show non-blocking UI error message
     */
    fun showError(context: Context, message: String)
}