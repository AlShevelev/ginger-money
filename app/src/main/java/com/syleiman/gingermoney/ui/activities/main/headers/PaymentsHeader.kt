package com.syleiman.gingermoney.ui.activities.main.headers

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.widgets.headers.HeaderBase

class PaymentsHeader
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : HeaderBase(context, attrs, defStyleAttr, R.layout.header_title_only) {

    companion object {
        /**
         * Create header and attach it to a toolbar
         */
        fun create(context: Context, title: CharSequence?, toolbar: Toolbar) = PaymentsHeader(
            context
        ).setup(title, toolbar)
    }
}