package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand

class StartSelectCurrencyDialogCommand(
    val selectedCurrency: Currency,
    val group: AccountGroup?
) : ViewCommand