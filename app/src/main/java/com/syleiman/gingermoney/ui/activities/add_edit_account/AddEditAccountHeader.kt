package com.syleiman.gingermoney.ui.activities.add_edit_account

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase
import kotlinx.android.synthetic.main.activity_add_edit_account_header.view.*

class AddEditAccountHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.activity_add_edit_account_header) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar, showDeleteButton: Boolean) =
            AddEditAccountHeader(context)
                .setup(title, toolbar, showDeleteButton)
    }

    private fun setup(title: CharSequence?, toolbar: Toolbar, showDeleteButton: Boolean) {
        super.setup(title, toolbar)

        deleteButton.visibility = if(showDeleteButton) View.VISIBLE else View.GONE
    }
}