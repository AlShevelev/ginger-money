package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.global_entities.money.Money
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult

interface AddAccountModel: ModelBase {

    val nameMaxLen: Int

    val memoMaxLen: Int

    /**
     * Returns current value of default selected Currency
     */
    suspend fun getDefaultCurrency(): ModelCallResult<out Currency>

    fun getAllAccountGroups(): List<AccountGroup>

    fun getAllCurrencies(): List<Currency>

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    suspend fun save(group: AccountGroup?, name: String?, amount: Money, memo: String?): DisplayingError?
}