package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors

data class GroupListItem(
    override val id: Long,
    val accountGroup: AccountGroup,
    val amount: Money,
    val colors: TextColors
): ListItem