package com.syleiman.gingermoney.ui.common.view_commands

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError

/**
 * Show some error
 */
class ShowErrorCommand(
    val error: DisplayingError
) : ViewCommand