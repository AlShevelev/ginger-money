package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand

/**
 *
 */
data class ShowAmountKeyboard(
    val value: Money,
    val currencies: List<Currency>
): ViewCommand