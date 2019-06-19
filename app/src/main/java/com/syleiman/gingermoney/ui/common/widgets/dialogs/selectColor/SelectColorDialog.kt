package com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.dto.enums.Color

/**
 * Dialog for select color of text and its background
 * [resultCallback] text and background color
 */
class SelectColorDialog(
    private val context : Context,
    private val selectedTextColor: Color,
    private val selectedBackgroundColor: Color,
    private val sampleText: String,
    private val title: String,
    private val okButtonText: String,
    private val cancelButtonText: String,
    private val resultCallback: (TextColors?) -> Unit) {

    //Select dialog's style - colorAccent for options and ? for buttons
    fun show() : AlertDialog =
        AlertDialog
            .Builder(context, R.style.App_Activity_Main_Dialog_Theme)
            .setTitle(title)
            .setCancelable(true)
            .also { dialog ->
                // Inflate view
                SelectColorDialogView(context)
                    .apply {
                        setSampleText(sampleText)
                        textColor = selectedTextColor
                        backgroundColor = selectedBackgroundColor

                        dialog.setView(this)
                    }
            }
            .setPositiveButton(okButtonText) { dialog, _ ->
                (dialog as AlertDialog)
                    .apply {
                        val dialogView = this.findViewById<View>(R.id.selectColorDialogRoot)!!.parent as SelectColorDialogView
                        resultCallback(TextColors(dialogView.textColor, dialogView.backgroundColor))
                    }
            }
            .setNegativeButton(cancelButtonText) { _, _ -> resultCallback (null)}
            .setOnCancelListener { resultCallback(null) }
            .show()
}