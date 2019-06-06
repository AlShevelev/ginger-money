package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class AccountListItem(
    override val id: Long,
    var dbId: Long,
    val name: String,
    val amount: Money
): ListItem