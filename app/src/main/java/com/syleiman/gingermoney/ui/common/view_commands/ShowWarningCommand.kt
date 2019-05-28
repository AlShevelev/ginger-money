package com.syleiman.gingermoney.ui.common.view_commands

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError

/**
 * Show some warning
 */
class ShowWarningCommand(
    val warning: DisplayingError
) : ViewCommand