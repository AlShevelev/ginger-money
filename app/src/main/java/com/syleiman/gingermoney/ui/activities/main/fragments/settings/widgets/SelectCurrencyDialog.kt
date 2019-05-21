package com.syleiman.gingermoney.ui.activities.main.fragments.settings.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.widgets.DrawableRadioButton

/**
 * Dialog for selected one option from a list using radio-buttons
 * @param <T> item in the list
 */
class SelectCurrencyDialog(
    private val context : Context,
    private val selectedItem: Currency,
    private val title: String,
    private val okButtonText: String,
    private val cancelButtonText: String,
    private val resultCallback: (Currency?) -> Unit) {

    //Select dialog's style - colorAccent for options and ? for buttons
    @SuppressLint("InflateParams")
    fun show() : AlertDialog {
        return AlertDialog
            .Builder(context, R.style.App_Activity_Main_Dialog_Theme)
            .setTitle(title)
            .setCancelable(true)
            .apply {
                // Inflate view
                val inflater = context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_select_currency, null)

                dialogView.findViewWithTag<DrawableRadioButton>(selectedItem.toString()).isChecked = true

                this.setView(dialogView)
            }
            .setPositiveButton(okButtonText) { dialog, _ ->
                // Extract selected currency
                (dialog as AlertDialog)
                    .apply {
                        when {
                            this.findViewById<DrawableRadioButton>(R.id.usdSwitcher)!!.isChecked -> resultCallback(Currency.USD)
                            this.findViewById<DrawableRadioButton>(R.id.eurSwitcher)!!.isChecked -> resultCallback(Currency.EUR)
                            else -> resultCallback(Currency.RUB)
                        }
                    }
            }
            .setNegativeButton(cancelButtonText) { _, _ -> resultCallback (null)}
            .setOnCancelListener { resultCallback(null) }
            .show()
    }
}