package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand

class StartSelectCurrencyDialogCommand(
    val selectedCurrency: Currency
) : ViewCommand