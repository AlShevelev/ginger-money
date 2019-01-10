package com.syleiman.gingermoney.ui.common.uiUtils

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.utils.appResources.AppResourcesProviderInterface
import javax.inject.Inject

/**
 * Common UI helper
 */
class UIUtils
@Inject
constructor(
    private val appResourcesProvider: AppResourcesProviderInterface
) : UIUtilsInterface {

    /**
     * Show non-blocking UI error message
     */
    @SuppressLint("InflateParams")
    override fun showError(context: Context, @StringRes messageResId: Int) =
        showError(context, appResourcesProvider.getString(messageResId))

    /**
     * Show non-blocking UI error message
     */
    @SuppressLint("InflateParams")
    override fun showError(context: Context, message: String) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val customView = inflater.inflate(R.layout.toast_error, null)

        val textView = customView.findViewById<TextView>(R.id.message_text)
        textView.text = message

        with(Toast(context)) {
            setGravity(Gravity.FILL_HORIZONTAL or Gravity.TOP, 0, 0)
            duration = Toast.LENGTH_LONG
            view = customView
            show()
        }
    }
}