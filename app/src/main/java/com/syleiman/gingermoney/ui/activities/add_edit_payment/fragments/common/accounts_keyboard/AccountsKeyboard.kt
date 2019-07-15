package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.arch.core.util.Cancellable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.core.utils.app_resources.AppResourcesProvider
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent
import javax.inject.Inject

/**
 * Popup for input an amount
 */
class AccountsKeyboard (
    private val rootView: View,
    private val context: Context,
    accounts: List<AccountListItem>,
    private val keyboardEventsProcessor: AccountsKeyboardEventsProcessor
) : PopupWindow(context) {

    private var backPressedCallbackCancellation: Cancellable? = null

    private val columns = 3

    @Inject
    internal lateinit var appResourceProvider: AppResourcesProvider

    init{
        App.injections.get<AddEditPaymentActivityComponent>().inject(this)

        @SuppressLint("InflateParams")
        contentView = (context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.popup_accounts_keyboard, null, false)

        setSize(accounts.size)
        setBackgroundDrawable(null)

        animationStyle = R.style.amountKeyboardAnimation

        contentView.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            keyboardEventsProcessor.onCloseAccountKeyboardClick()
        }

        // Init list
        val layoutManager = GridLayoutManager(context, columns)

        val adapter = AccountsListAdapter(keyboardEventsProcessor, accounts)
        adapter.setHasStableIds(true)

        val list = contentView.findViewById<RecyclerView>(R.id.itemsList)
        list.isSaveEnabled = false
        list.itemAnimator = null
        list.layoutManager = layoutManager
        list.adapter = adapter
    }

    fun show() {
        showAtLocation(rootView, Gravity.BOTTOM, 0, 0)

        // Processes Back button action
        backPressedCallbackCancellation = (context as? AppCompatActivity)?.onBackPressedDispatcher?.addCallback {
            hide()               // Closes the popup
            true
        }
    }

    fun hide() {
        dismiss()
    }

    /**
     * Manually sets the popup window size
     */
    private fun setSize(accountsTotal: Int) {
        width = WindowManager.LayoutParams.MATCH_PARENT

        var rows = (accountsTotal / columns)
        if(accountsTotal % columns != 0) {
            rows++
        }

        if(rows > 3) {
            rows = 3
        }

        height = (rows * appResourceProvider.getDimension(R.dimen.accountKeyboardItem).toInt()) +
                appResourceProvider.getDimension(R.dimen.headerButtonSize).toInt()
    }
}
