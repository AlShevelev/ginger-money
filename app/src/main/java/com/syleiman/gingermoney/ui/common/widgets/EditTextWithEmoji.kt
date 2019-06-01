package com.syleiman.gingermoney.ui.common.widgets

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.shevelev.alpha_emoji_panel.EmojiActions
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.ui.common.extension.setOnTextChangeListener
import kotlinx.android.synthetic.main.widget_edittext_with_button.view.*


/**
 * RadioButton with an icon on the right
 */
class EditTextWithEmoji
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val emojiActions: EmojiActions

    private var _onFocusChangeListener: OnFocusChangeListener? = null

    var text: Editable?
    get() = nameTextField.text
    set(value) {
        nameTextField.text = value
    }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        @Suppress("LeakingThis")
        inflater.inflate(R.layout.widget_edittext_with_button, this)

        emojiActions = EmojiActions(context, rootView, smileIcon, nameTextField)
        emojiActions.setUpEmojiKeyboard()

        nameTextField.setOnFocusChangeListener { v, hasFocus -> _onFocusChangeListener?.onFocusChange(v, hasFocus)  }
    }

    override fun clearFocus() = nameTextField.clearFocus()

    fun hideEmojiKeyboard() = emojiActions.hide()

    override fun setOnFocusChangeListener(listener: OnFocusChangeListener?) {
        _onFocusChangeListener = listener
    }

    fun setOnEmojiKeyboardOpenListener(listener: (() -> Unit)?) = emojiActions.setOnKeyboardOpenListener(listener)

    fun setOnTextChangeListener(listener: (String) -> Unit) = nameTextField.setOnTextChangeListener(listener)

    fun setTextMaxLength(maxLength: Int) {
        val filters = arrayOfNulls<InputFilter>(1)
        filters[0] = InputFilter.LengthFilter(maxLength)
        nameTextField.filters = filters
    }
}