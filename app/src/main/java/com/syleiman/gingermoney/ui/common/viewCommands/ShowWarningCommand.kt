package com.syleiman.gingermoney.ui.common.viewCommands

import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError

/**
 * Show some warning
 */
data class ShowWarningCommand(val warning: DisplayingError) : ViewCommand