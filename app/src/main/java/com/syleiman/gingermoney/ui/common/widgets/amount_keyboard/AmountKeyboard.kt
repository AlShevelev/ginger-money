package com.syleiman.gingermoney.ui.common.widgets.amount_keyboard

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Cancellable
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProviderInterface
import com.syleiman.gingermoney.ui.common.ui_utils.UIUtilsInterface
import com.syleiman.gingermoney.ui.dependency_injection.UIComponent
import javax.inject.Inject

/**
 *
 */
class AmountKeyboard (
    private val rootView: View,
    private val context: Context
) : PopupWindow(context) {

    private var backPressedCallbackCancellation: Cancellable? = null

    @Inject
    internal lateinit var appResourceProvider: AppResourcesProviderInterface

    @Inject
    internal lateinit var uiUtilsInterface: UIUtilsInterface

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

    /**
     *
     */
    fun show() {
        uiUtilsInterface.setSoftKeyboardVisibility(context, rootView, false)
        showAtLocation(rootView, Gravity.BOTTOM, 0, 0)

        // Processes Back button action
        backPressedCallbackCancellation = (context as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback {
            hide()               // Closes the popup
            true
        }

    }

    /**
     *
     */
    fun hide() {
        dismiss()

        backPressedCallbackCancellation?.cancel()
        backPressedCallbackCancellation = null
    }

    /**
     * Manually sets the popup window size
     */
    private fun setSize() {
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = appResourceProvider.getDimension(R.dimen.amountKeyboardHeight).toInt()
    }

    /**
     *
     */
    private fun onKeyClick(code: AmountKeyboardKeyCode) {
        if(code == AmountKeyboardKeyCode.KEY_DONE) {
            hide()
        }
    }
}
