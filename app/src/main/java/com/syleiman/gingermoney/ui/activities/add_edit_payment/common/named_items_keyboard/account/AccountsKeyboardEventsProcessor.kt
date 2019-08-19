package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.account

import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedItemsKeyboardEventsProcessor

interface AccountsKeyboardEventsProcessor: NamedItemsKeyboardEventsProcessor {
    fun onAccountSelect(id: Long)

    fun onCloseAccountKeyboard()
}