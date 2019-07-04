package com.syleiman.gingermoney.ui.common.ui_utils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
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
    private val appContext: Context,
    private val appResourcesProvider: AppResourcesProvider
) : UIUtils {

    /**
     * Show non-blocking UI error message
     */
    override fun showError(@StringRes messageResId: Int) = showError(appResourcesProvider.getString(messageResId))

    /**
     * Show non-blocking UI error message
     */
    override fun showError(message: String) = showCustomToast(message, CustomToastType.ERROR)

    /**
     * Show non-blocking UI warning message
     */
    override fun showWarning(@StringRes messageResId: Int) = showWarning(appResourcesProvider.getString(messageResId))

    /**
     * Show non-blocking UI error warning
     */
    override fun showWarning(message: String) = showCustomToast(message, CustomToastType.WARNING)

    @SuppressLint("InflateParams")
    private fun showCustomToast(message: String, type: CustomToastType) {
        val inflater = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val customView = when(type) {
            CustomToastType.ERROR -> inflater.inflate(R.layout.toast_error, null)
            CustomToastType.WARNING -> inflater.inflate(R.layout.toast_warning, null)
        }

        val textView = customView.findViewById<TextView>(R.id.message_text)
        textView.text = message

        with(Toast(appContext)) {
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

    override fun setSoftKeyboardVisibility(someViewInWindow: View, isVisible: Boolean) {
        someViewInWindow.post(SoftKeyboardVisibilityRunnable(appContext, someViewInWindow, isVisible))
    }
}