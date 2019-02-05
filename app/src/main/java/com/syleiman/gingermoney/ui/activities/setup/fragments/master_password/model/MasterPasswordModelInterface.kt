package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model

import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface MasterPasswordModelInterface : ModelBaseInterface {
    /**
     *
     */
    val passwordMaxLen: Int

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    fun savePassword(password: String?, resultCall: (DisplayingError?) -> Unit)
}