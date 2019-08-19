package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard

import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class NamedListItem(
    override val id: Long,
    val name: String
): ListItem