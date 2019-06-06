package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.dto

import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.dto.enums.Color
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem

data class GroupListItem(
    override val id: Long,
    val accountGroup: AccountGroup,
    val amount: Money,
    val backgroundColor: Color,
    val foregroundColor: Color
): ListItem