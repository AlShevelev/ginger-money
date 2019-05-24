package com.syleiman.gingermoney.ui.activities.add_edit_account.fragments.add.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.dto.enums.AccountGroup
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import javax.inject.Inject

/**
 *
 */
class AddAccountModel
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface
) : ModelBase(), AddAccountModelInterface {

    /**
     * Returns current value of default selected Currency
     */
    override suspend fun getDefaultCurrency(): ModelCallResult<out Currency> =
        getValue {
            keyValueStorage.getDefaultCurrency()
        }

    /**
     *
     */
    override fun getAllAccountGroups(): List<AccountGroup> =
        listOf(
            AccountGroup.CASH,
            AccountGroup.CARDS,
            AccountGroup.CREDIT_CARDS,
            AccountGroup.DEBIT_CARDS,
            AccountGroup.ACCOUNTS,
            AccountGroup.DEPOSITS,
            AccountGroup.SAVINGS,
            AccountGroup.INVESTMENTS,
            AccountGroup.SHARES,
            AccountGroup.BONDS,
            AccountGroup.OTHER)

    /**
     *
     */
    override fun getAllCurrencies(): List<Currency> = listOf(Currency.USD, Currency.EUR, Currency.RUB)

}