package com.syleiman.gingermoney.ui.common.widgets.amount_keyboard

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.core.global_entities.money.Currency
import kotlinx.android.synthetic.main.popup_amount_keyboard.view.*
import java.lang.UnsupportedOperationException

class AmountKeyboardView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    View.OnClickListener {

    private var onKeyClickListener: ((AmountKeyboardKeyCode) -> Unit)? = null

    init {
        inflate(context, R.layout.popup_amount_keyboard, this)

        digit0Button.setOnClickListener(this)
        digit1Button.setOnClickListener(this)
        digit2Button.setOnClickListener(this)
        digit3Button.setOnClickListener(this)
        digit4Button.setOnClickListener(this)
        digit5Button.setOnClickListener(this)
        digit6Button.setOnClickListener(this)
        digit7Button.setOnClickListener(this)
        digit8Button.setOnClickListener(this)
        digit9Button.setOnClickListener(this)

        doneButton.setOnClickListener(this)
        switchCurrencyButton.setOnClickListener(this)
        backspaceButton.setOnClickListener(this)
        clearAllButton.setOnClickListener(this)
        dotButton.setOnClickListener(this)
        signButton.setOnClickListener(this)
    }

    fun setOnKeyClickListener(listener: ((AmountKeyboardKeyCode) -> Unit)?) {
        onKeyClickListener = listener
    }

    fun setButtonState(code: AmountKeyboardKeyCode, isEnabled: Boolean) {
        val button = when(code) {
            AmountKeyboardKeyCode.KEY_0 -> digit0Button
            AmountKeyboardKeyCode.KEY_1 -> digit1Button
            AmountKeyboardKeyCode.KEY_2 -> digit2Button
            AmountKeyboardKeyCode.KEY_3 -> digit3Button
            AmountKeyboardKeyCode.KEY_4 -> digit4Button
            AmountKeyboardKeyCode.KEY_5 -> digit5Button
            AmountKeyboardKeyCode.KEY_6 -> digit6Button
            AmountKeyboardKeyCode.KEY_7 -> digit7Button
            AmountKeyboardKeyCode.KEY_8 -> digit8Button
            AmountKeyboardKeyCode.KEY_9 -> digit9Button

            AmountKeyboardKeyCode.KEY_DONE -> doneButton
            AmountKeyboardKeyCode.KEY_CURRENCY -> switchCurrencyButton
            AmountKeyboardKeyCode.KEY_BACKSPACE -> backspaceButton
            AmountKeyboardKeyCode.KEY_CLEAN -> clearAllButton
            AmountKeyboardKeyCode.KEY_DOT -> dotButton
            AmountKeyboardKeyCode.KEY_SIGN -> signButton
        }

        button.isEnabled = isEnabled
    }

    fun setCurrency(currency: Currency) {
        switchCurrencyButton.text = currency.symbol.toString()
    }

    override fun onClick(view: View?) {
        view.let { 
            val code = when(it) {
                digit0Button -> AmountKeyboardKeyCode.KEY_0
                digit1Button -> AmountKeyboardKeyCode.KEY_1
                digit2Button -> AmountKeyboardKeyCode.KEY_2
                digit3Button -> AmountKeyboardKeyCode.KEY_3
                digit4Button -> AmountKeyboardKeyCode.KEY_4
                digit5Button -> AmountKeyboardKeyCode.KEY_5
                digit6Button -> AmountKeyboardKeyCode.KEY_6
                digit7Button -> AmountKeyboardKeyCode.KEY_7
                digit8Button -> AmountKeyboardKeyCode.KEY_8
                digit9Button -> AmountKeyboardKeyCode.KEY_9

                doneButton -> AmountKeyboardKeyCode.KEY_DONE
                switchCurrencyButton -> AmountKeyboardKeyCode.KEY_CURRENCY
                backspaceButton -> AmountKeyboardKeyCode.KEY_BACKSPACE
                clearAllButton -> AmountKeyboardKeyCode.KEY_CLEAN
                dotButton -> AmountKeyboardKeyCode.KEY_DOT
                signButton -> AmountKeyboardKeyCode.KEY_SIGN

                else -> throw UnsupportedOperationException("This view is not supported")
            }

            onKeyClickListener?.invoke(code)
        }    
    }
}