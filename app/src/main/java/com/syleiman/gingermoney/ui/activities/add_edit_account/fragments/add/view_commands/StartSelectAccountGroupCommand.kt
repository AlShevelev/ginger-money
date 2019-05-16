package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.view_commands

import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand

/**
 *
 */
data class StartSelectAccountGroupCommand(
    val groups: List<AccountGroup>
) : ViewCommand