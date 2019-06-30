package com.syleiman.gingermoney.ui.activities.add_edit_payment

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.widgets.HeaderBase
import kotlinx.android.synthetic.main.activity_add_edit_payment_header.view.*

class AddEditPaymentHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.activity_add_edit_payment_header) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar, showAddButton: Boolean) =
            AddEditPaymentHeader(context).setup(title, toolbar, showAddButton)
    }

    private fun setup(title: CharSequence?, toolbar: Toolbar, showAddButton: Boolean) {
        super.setup(title, toolbar)

        addButton.visibility = if(showAddButton) View.VISIBLE else View.GONE
    }
}