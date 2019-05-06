package com.syleiman.gingermoney.ui.common.controls

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.ui.activities.main.headers.HeaderTags

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
    }

    init {
        inflate(context, layoutResId, this)
    }

    /**
     *
     */
    open fun detachFromFragment() {}

    /**
     *
     */
    protected fun setup(title: CharSequence?, toolbar: Toolbar) {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        layoutParams = params
        findViewWithTag<TextView>(TITLE_TAG).text = title
        tag = HeaderTags.CURRENT_HEADER

        toolbar.addView(this)
    }
}