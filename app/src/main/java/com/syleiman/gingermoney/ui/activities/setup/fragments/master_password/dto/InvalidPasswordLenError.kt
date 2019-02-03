package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.dto

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError

/**
 * Invalid length of a password
 */
data class InvalidPasswordLenError(
    val minPasswordLen: Int,
    val maxPasswordLen: Int
) : DisplayingError