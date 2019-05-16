package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

/**
 *
 */
interface AddAccountModelInterface: ModelBaseInterface {
    /**
     * Returns current value of default selected Currency
     */
    suspend fun getDefaultCurrency(): ModelCallResult<out Currency>

    /**
     *
     */
    fun getAllAccountGroups(): List<AccountGroup>
}