package com.syleiman.gingermoney.ui.activities.add_edit_payment.common.view_commands

import com.syleiman.gingermoney.ui.activities.add_edit_payment.common.named_items_keyboard.NamedListItem
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand
import org.threeten.bp.ZonedDateTime

class ShowAccountsKeyboard(
    val items: List<NamedListItem>
) : ViewCommand

class ShowCategoriesKeyboard(
    val items: List<NamedListItem>
) : ViewCommand

class StartSelectingDateCommand(val dateTime: ZonedDateTime): ViewCommand

class MoveToListOfCategoriesCommand(): ViewCommand

class MoveToAddCategoryCommand(): ViewCommand

class MoveToEditCategoryCommand(val categoryDbId: Long): ViewCommand

/**
 * Hides set of keyboard
 */
class HideKeyboard(vararg val args: Keyboard): ViewCommand
