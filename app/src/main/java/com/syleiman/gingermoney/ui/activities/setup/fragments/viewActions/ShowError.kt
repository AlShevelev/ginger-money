package com.syleiman.gingermoney.ui.activities.setup.fragments.viewActions

import com.syleiman.gingermoney.ui.common.ViewCommand
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError

/**
 * Show some error
 */
data class ShowError(val error: DisplayingError) : ViewCommand