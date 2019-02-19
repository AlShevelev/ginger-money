package com.syleiman.gingermoney.ui.common.ui_utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

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

    /**
     * Show non-blocking UI warning message
     */
    fun showWarning(context: Context, @StringRes messageResId: Int)

    /**
     * Show non-blocking UI warning message
     */
    fun showWarning(context: Context, message: String)

    /** Shows dialog with a list of options
     * @param [resultCallback] index of selected item (null if user canceled dialog) */
    fun showOneOptionRadioDialog(
        context: Context,
        items: List<String>,
        selectedIndex: Int,
        title: String?,
        resultCallback: (Int?) -> Unit) : AlertDialog
}