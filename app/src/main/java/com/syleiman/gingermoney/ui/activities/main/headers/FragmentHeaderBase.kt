package com.syleiman.gingermoney.ui.activities.main.headers

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R

/** Base class for all fragment headers */
abstract class FragmentHeaderBase
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @LayoutRes layoutResId: Int
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        @Suppress("LeakingThis")
        inflater.inflate(layoutResId, this)
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
        findViewById<TextView>(R.id.headerTitle).text = title
        tag = HeaderTags.CURRENT_HEADER

        toolbar.addView(this)
    }
}