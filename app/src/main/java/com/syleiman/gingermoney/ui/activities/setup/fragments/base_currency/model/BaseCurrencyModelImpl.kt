package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import javax.inject.Inject

class BaseCurrencyModelImpl
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacade
) : ModelBaseImpl(),
    BaseCurrencyModel {

    override val startCurrencyValue: Currency = Currency.USD

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveCurrency(currency: Currency): DisplayingError? =
        saveValue {
            keyValueStorage.setDefaultCurrency(currency)
        }
}