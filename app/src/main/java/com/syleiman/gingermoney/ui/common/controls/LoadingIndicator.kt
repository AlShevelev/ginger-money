package com.syleiman.gingermoney.ui.common.controls

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.syleiman.gingermoney.R

/**
 * Full screen loading indicator
 */
/** Base class for all headers */
final class LoadingIndicator
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.common_loading_indicator, this)
    }
}