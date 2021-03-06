package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.common.dto.view_commands

import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.mvvm.view_commands.ViewCommand

class StartSelectAccountGroupCommand(
    val groups: List<AccountGroup>
) : ViewCommand