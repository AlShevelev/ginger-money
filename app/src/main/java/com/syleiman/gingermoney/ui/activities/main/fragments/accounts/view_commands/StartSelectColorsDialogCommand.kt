package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.view_commands

import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.widgets.dialogs.selectColor.TextColors
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand

class StartSelectColorsDialogCommand(
    val colors: TextColors,
    val group: AccountGroup
) : ViewCommand