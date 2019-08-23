package com.syleiman.gingermoney.ui.common.mvvm.view_commands

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError

/**
 * Restricting interface for all direct actions from ViewModel to View
 */
interface ViewCommand

/**
 * Move back on navigation stack
 */
class MoveBackViewCommand : ViewCommand

/**
 * Show some error
 */
class ShowErrorCommand(
    val error: DisplayingError
) : ViewCommand

/**
 * Show some warning
 */
class ShowWarningCommand(
    val warning: DisplayingError
) : ViewCommand

class HideAmountKeyboard: ViewCommand

class ShowAmountKeyboard(
    val value: Money,
    val currencies: List<Currency>,
    val canEditCurrency: Boolean,
    val canEditSign: Boolean
): ViewCommand

class HideEmojiKeyboard: ViewCommand

class HideSoftKeyboard: ViewCommand