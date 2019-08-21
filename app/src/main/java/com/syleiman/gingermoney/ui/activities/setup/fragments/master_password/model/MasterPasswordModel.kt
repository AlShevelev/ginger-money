package com.syleiman.gingermoney.ui.activities.setup.fragments.master_password.model

import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase

interface MasterPasswordModel: ModelBase {

    val passwordMaxLen: Int

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    suspend fun savePassword(password: String?): DisplayingError?
}