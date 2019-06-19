package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.recycler_view.ListItem
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface AccountsModelInterface: ModelBaseInterface {

    suspend fun getListItems(): ModelCallResult<out List<ListItem>>

    suspend fun getCurrency(group: AccountGroup?): Currency

    suspend fun getColors(group: AccountGroup): ModelCallResult<out TextColors>

    suspend fun updateCurrency(group: AccountGroup?, currency: Currency): DisplayingError?

    suspend fun updateColors(group: AccountGroup, colors: TextColors): DisplayingError?
}