package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface AccountsModelInterface : ModelBaseInterface {
    /**
     *
     */
    fun getAllAccounts(resultCallback: (List<Account>?, DisplayingError?) -> Unit)
}