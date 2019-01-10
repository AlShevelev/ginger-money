package com.syleiman.gingermoney.ui.activities.setup.fragments.masterPassword.dto

import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError

/**
 * Invalid length of a password
 */
data class InvalidPasswordLenError(
    val minPasswordLen: Int,
    val maxPasswordLen: Int
) : DisplayingError