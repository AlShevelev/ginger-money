package com.syleiman.gingermoney.ui.activities.main.fragments.accounts.model

import com.syleiman.gingermoney.dto.entities.Account
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

/**
 *
 */
interface AccountsModelInterface: ModelBaseInterface {
    /**
     *
     */
    suspend fun getAllAccounts(): ModelCallResult<out List<Account>>
}