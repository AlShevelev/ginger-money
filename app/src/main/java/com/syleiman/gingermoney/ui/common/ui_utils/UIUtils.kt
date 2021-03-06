package com.syleiman.gingermoney.ui.common.ui_utils

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog

/**
 * Common UI helper
 */
interface UIUtils {
    /**
     * Show non-blocking UI error message
     */
    fun showError(@StringRes messageResId: Int)

    /**
     * Show non-blocking UI error message
     */
    fun showError(message: String)

    /**
     * Show non-blocking UI warning message
     */
    fun showWarning(@StringRes messageResId: Int)

    /**
     * Show non-blocking UI warning message
     */
    fun showWarning(message: String)

    /** Shows dialog with a list of options and one option is selected
     * @param [resultCallback] index of selected item (null if user canceled dialog) */
    fun showOneOptionRadioDialog(
        context: Context,
        items: List<String>,
        selectedIndex: Int,
        title: String?,
        resultCallback: (Int?) -> Unit
    ): AlertDialog

    /** Shows dialog with a list of options
     * @param [resultCallback] index of selected item (null if user canceled dialog) */
    fun showOneOptionDialog(
        context: Context,
        items: List<String>,
        title: String?,
        resultCallback: (Int?) -> Unit
    ): AlertDialog

    fun setSoftKeyboardVisibility(someViewInWindow: View, isVisible: Boolean)
}