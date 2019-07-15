package com.syleiman.gingermoney.ui.activities.add_edit_payment.fragments.common.accounts_keyboard

interface AccountsKeyboardEventsProcessor {
    fun onAccountItemClick(id: Long)

    fun onCloseAccountKeyboardClick()
}