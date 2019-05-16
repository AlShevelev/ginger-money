package com.syleiman.gingermoney.ui.common.controls.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.syleiman.gingermoney.R

/**
 * Dialog for selected one option from a list
 * @param <T> item in the list
 */
class OneOptionDialog<T>(
    private val context : Context,
    private val items: List<T>,
    private val title: String?,
    private val resultCallback: (Int?) -> Unit,
    private val itemToStringMapper: (T) -> CharSequence) {

    //Select dialog's style - colorAccent for options and ? for buttons
    fun show() : AlertDialog =
        AlertDialog
            .Builder(context, R.style.App_Activity_Main_Dialog_Theme)
            .apply {
                title?.let {
                    this.setTitle(it)
                }
            }
            .setCancelable(true)
            .setItems(createItems()) { _, index -> resultCallback(index) }
            .setOnCancelListener { resultCallback(null) }
            .show()

    /** Create text items for dialog  */
    private fun createItems(): Array<CharSequence> = items.map { itemToStringMapper(it) }.toTypedArray()
}