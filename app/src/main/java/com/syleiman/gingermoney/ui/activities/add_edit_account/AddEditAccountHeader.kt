package com.syleiman.gingermoney.ui.activities.add_edit_account

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.widgets.HeaderBase

/** Show current page as a set of dots */
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
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = AddEditAccountHeader(context).setup(title, toolbar)
    }
}