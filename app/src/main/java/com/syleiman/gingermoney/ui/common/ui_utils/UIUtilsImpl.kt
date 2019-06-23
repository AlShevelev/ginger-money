package com.syleiman.gingermoney.ui.common.ui_utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.common.widgets.dialogs.OneOptionDialog
import com.syleiman.gingermoney.ui.common.widgets.dialogs.OneOptionRadioDialog
import javax.inject.Inject

/**
 * Common UI helper
 */
class UIUtilsImpl
@Inject
constructor(
    private val appResourcesProvider: AppResourcesProvider
) : UIUtils {

    /**
     * Show non-blocking UI error message
     */
    override fun showError(context: Context, @StringRes messageResId: Int) =
        showError(context, appResourcesProvider.getString(messageResId))

    /**
     * Show non-blocking UI error message
     */
    override fun showError(context: Context, message: String) = showCustomToast(context, message, CustomToastType.ERROR)

    /**
     * Show non-blocking UI warning message
     */
    override fun showWarning(context: Context, @StringRes messageResId: Int) =
        showWarning(context, appResourcesProvider.getString(messageResId))

    /**
     * Show non-blocking UI error warning
     */
    override fun showWarning(context: Context, message: String) = showCustomToast(context, message, CustomToastType.WARNING)

    @SuppressLint("InflateParams")
    private fun showCustomToast(context: Context, message: String, type: CustomToastType) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val customView = when(type) {
            CustomToastType.ERROR -> inflater.inflate(R.layout.toast_error, null)
            CustomToastType.WARNING -> inflater.inflate(R.layout.toast_warning, null)
        }

        val textView = customView.findViewById<TextView>(R.id.message_text)
        textView.text = message

        with(Toast(context)) {
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
            duration = Toast.LENGTH_SHORT
            view = customView
            show()
        }
    }

    /** Shows dialog with a list of options
     * @param [resultCallback] index of selected item (null if user canceled dialog) */
    override fun showOneOptionRadioDialog(
        context: Context,
        items: List<String>,
        selectedIndex: Int,
        title: String?,
        resultCallback: (Int?) -> Unit) : AlertDialog {

        val okButtonText = appResourcesProvider.getString(R.string.commonOk)
        val cancelButtonText = appResourcesProvider.getString(R.string.commonCancel)

        return OneOptionRadioDialog(
            context,
            items,
            title,
            okButtonText,
            cancelButtonText,
            resultCallback,
            {it},
            selectedIndex)
        .show()
    }

    /** Shows dialog with a list of options
     * @param [resultCallback] index of selected item (null if user canceled dialog) */
    override fun showOneOptionDialog(
        context: Context,
        items: List<String>,
        title: String?,
        resultCallback: (Int?) -> Unit
    ): AlertDialog =
        OneOptionDialog(
            context,
            items,
            title,
            resultCallback,
            { it })
        .show()

    override fun setSoftKeyboardVisibility(context: Context, someViewInWindow: View, isVisible: Boolean) {
        if (isVisible) {
            someViewInWindow.post(KeyboardVisibilityRunnable(context, someViewInWindow))
        } else {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(someViewInWindow.windowToken, 0)
        }
    }
}