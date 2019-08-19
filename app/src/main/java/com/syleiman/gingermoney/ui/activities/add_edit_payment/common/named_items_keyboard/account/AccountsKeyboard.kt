package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import com.syleiman.gingermoney.R
import com.syleiman.gingermoney.application.App
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsKeyboard
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsListAdapterBase
import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.activities.add_edit_payment.dependency_injection.AddEditPaymentActivityComponent

class AccountsKeyboard(
    rootView: View,
    context: Context,
    items: List<NamedListItem>,
    keyboardEventsProcessor: AccountsKeyboardEventsProcessor
): NamedItemsKeyboard<AccountsKeyboardEventsProcessor>(
    rootView,
    context,
    items,
    keyboardEventsProcessor) {

    override fun inject() = App.injections.get<AddEditPaymentActivityComponent>().inject(this)

    override fun provideLayout(): Int = R.layout.popup_accounts_keyboard

    override fun setSize(accountsTotal: Int) {
        val maxRows = 3

        width = WindowManager.LayoutParams.MATCH_PARENT

        var rows = (accountsTotal / columns)
        if(accountsTotal % columns != 0) {
            rows++
        }

        if(rows > maxRows) {
            rows = maxRows
        }

        height = (rows * appResourceProvider.getDimension(R.dimen.accountKeyboardItem).toInt()) +
                appResourceProvider.getDimension(R.dimen.headerButtonSize).toInt()
    }

    override fun setHeaderButtonsListeners(keyboardEventsProcessor: AccountsKeyboardEventsProcessor) {
        contentView.findViewById<ImageButton>(R.id.closeButton).setOnClickListener {
            keyboardEventsProcessor.onCloseAccountKeyboard()
        }
    }

    override fun createAdapter(
        items: List<NamedListItem>,
        keyboardEventsProcessor: AccountsKeyboardEventsProcessor
    ): NamedItemsListAdapterBase<AccountsKeyboardEventsProcessor> =
        AccountsListAdapterBase(keyboardEventsProcessor, items)
}