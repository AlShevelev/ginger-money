package com.syleiman.gingermoney.ui.activities.login.fragments.master_password.model

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface MasterPasswordModelInterface : ModelBaseInterface {
    /**
     *
     */
    val isFingerprintAuthenticationPossible: Boolean

    /**
     *
     */
    val passwordMaxLen: Int

    /**
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    fun login(password: String?, result: (DisplayingError?) -> Unit)
}