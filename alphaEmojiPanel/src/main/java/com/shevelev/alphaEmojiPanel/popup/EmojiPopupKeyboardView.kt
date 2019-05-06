package com.shevelev.alphaEmojiPanel.popup

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.shevelev.alphaEmojiPanel.R

/**
 * Custom view for [EmojiPopupKeyboard]
 */
class EmojiPopupKeyboardView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.popup_emoji, this)
    }
}
