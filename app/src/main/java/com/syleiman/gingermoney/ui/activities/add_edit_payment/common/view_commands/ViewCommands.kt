package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands

import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand
import org.threeten.bp.ZonedDateTime

class ShowAccountsKeyboard(
    val items: List<NamedListItem>
) : ViewCommand

class HideAccountsKeyboard: ViewCommand

class ShowCategoriesKeyboard(
    val items: List<NamedListItem>
) : ViewCommand

class HideCategoriesKeyboard: ViewCommand

class StartSelectingDateCommand(val dateTime: ZonedDateTime): ViewCommand

class MoveToListOfCategoriesCommand(): ViewCommand