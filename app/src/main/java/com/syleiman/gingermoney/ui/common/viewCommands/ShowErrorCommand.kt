package com.syleiman.gingermoney.ui.common.viewCommands

import com.syleiman.gingermoney.ui.common.viewCommands.ViewCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError

/**
 * Show some error
 */
data class ShowErrorCommand(val error: DisplayingError) : ViewCommand