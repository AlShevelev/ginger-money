package com.syleiman.gingermoney.ui.common.widgets.headers

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout

/** Base class for all headers */
abstract class HeaderBase
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @LayoutRes layoutResId: Int
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val TITLE_TAG = "TITLE"
        const val CURRENT_HEADER_TAG = "CURRENT_HEADER"
    }

    init {
        inflate(context, layoutResId, this)
    }

    open fun detachFromFragment() {}

    @CallSuper
    protected open fun setup(title: CharSequence?, toolbar: Toolbar) {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        layoutParams = params
        findViewWithTag<TextView>(TITLE_TAG).text = title
        tag = CURRENT_HEADER_TAG

        toolbar.addView(this)
    }
}