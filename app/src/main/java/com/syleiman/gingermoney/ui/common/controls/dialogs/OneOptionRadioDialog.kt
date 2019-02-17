package com.syleiman.gingermoney.ui.common.controls.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.syleiman.gingermoney.R

/**
 * Dialog for selected one option from a list using radio-buttons
 * @param <T> item in the list
 */
class OneOptionRadioDialog<T>(
    private val context : Context,
    private val items: List<T>,
    private val title: String?,
    private val okButtonText: String,
    private val cancelButtonText: String?,
    private val resultCallback: (Int?) -> Unit,
    private val itemToStringMapper: (T) -> CharSequence,
    private var selectedIndex: Int) {

    //Select dialog's style - colorAccent for options and ? for buttons
    fun show() {
        AlertDialog
            .Builder(context, R.style.App_Activity_Main_Dialog_Theme)
            .apply {
                title?.let {
                    this.setTitle(it)
                }
            }
            .setCancelable(true)
            .setSingleChoiceItems(createItems(), selectedIndex) { _, index -> selectedIndex = index }
            .setPositiveButton(okButtonText) { _, _ -> resultCallback(selectedIndex) }
            .apply {
                cancelButtonText?.let {
                    this.setNegativeButton(cancelButtonText) { _, _ -> resultCallback (null)}
                }
            }
            .setOnCancelListener { resultCallback(null) }
            .show()
    }

    /** Create text items for dialog  */
    private fun createItems(): Array<CharSequence> = items.map { itemToStringMapper(it) }.toTypedArray()
}