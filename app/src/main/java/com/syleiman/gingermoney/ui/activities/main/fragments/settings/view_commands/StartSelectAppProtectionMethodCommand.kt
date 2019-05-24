package com.syleiman.gingermoney.ui.activities.main.fragments.settings.view_commands

import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.view_commands.ViewCommand

data class StartSelectAppProtectionMethodCommand(
    val selectedIndex: Int,
    val protectionMethods: List<AppProtectionMethod>
) : ViewCommand