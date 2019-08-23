package com.syleiman.gingermoney.ui.common.widgets.amount_keyboard

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import javax.inject.Inject

/**
 * Popup for input an amount
 */
class AmountKeyboard (
    private val rootView: View,
    private val context: Context,
    private val currencies: List<Currency>,
    private val canEditCurrency: Boolean,
    private val canEditSign: Boolean
) : PopupWindow(context) {

    private var backPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            hide()               // Closes the popup
        }
    }

    private var isOpened = false

    private val view: AmountKeyboardView
    get() = contentView as AmountKeyboardView

    private var onEditingListener: ((AmountKeyboardEditingResult) -> Unit)? = null

    private lateinit var currentState: AmountKeyboardMoney

    @Inject
    internal lateinit var appResourceProvider: AppResourcesProvider

    init{
        App.injections.get<UIComponent>().inject(this)

        contentView = AmountKeyboardView(context)
            .also {
                it.setOnKeyClickListener { keyCode -> onKeyClick(keyCode) }
            }

        setSize()
        setBackgroundDrawable(null)

        animationStyle = R.style.amountKeyboardAnimation
    }

    fun show(value: Money) {
        if(isOpened) {
            return
        }

        currentState = AmountKeyboardMoney(value)

        onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))

        view.setCurrency(getNextCurrency(currentState.currency))

        view.setButtonState(AmountKeyboardKeyCode.KEY_CURRENCY, canEditCurrency)
        view.setButtonState(AmountKeyboardKeyCode.KEY_SIGN, canEditSign)

        showAtLocation(rootView, Gravity.BOTTOM, 0, 0)

        // Processes Back button action
        (context as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        isOpened = true
    }

    fun hide() {
        if(!isOpened) {
            return
        }

        dismiss()

        backPressedCallback.remove()

        onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))

        isOpened = false
    }

    fun setOnEditingListener(listener: ((AmountKeyboardEditingResult) -> Unit)) {
        onEditingListener = listener
    }

    /**
     * Manually sets the popup window size
     */
    private fun setSize() {
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = appResourceProvider.getDimension(R.dimen.amountKeyboardHeight).toInt()
    }

    private fun onKeyClick(code: AmountKeyboardKeyCode) {
        when(code) {
            AmountKeyboardKeyCode.KEY_DONE -> hide()

            AmountKeyboardKeyCode.KEY_CURRENCY -> {
                val newCurrency = getNextCurrency(currentState.currency)
                currentState = AmountKeyboardMoney(newCurrency.toMoney(currentState.toMoney().totalCents))

                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
                view.setCurrency(getNextCurrency(newCurrency))
            }

            AmountKeyboardKeyCode.KEY_SIGN -> {
                currentState.changeSign()
                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
            }

            AmountKeyboardKeyCode.KEY_BACKSPACE -> {
                currentState.processBackspace()
                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
            }

            AmountKeyboardKeyCode.KEY_DOT -> {
                currentState.processDot()
                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
            }

            AmountKeyboardKeyCode.KEY_CLEAN -> {
                currentState = AmountKeyboardMoney(currentState.currency.toMoney(0))
                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
            }

            else -> {
                currentState.processDigit(code)
                onEditingListener?.invoke(AmountKeyboardEditingResult(currentState.toMoney(), currentState.hasCents))
            }
        }
    }

    private fun getNextCurrency(current: Currency): Currency {
        val index = currencies.indexOf(current) + 1
        return if(index < currencies.size) {
            currencies[index]
        } else {
            currencies[0]
        }
    }
}
