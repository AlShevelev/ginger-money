package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import javax.inject.Inject

/**
 *
 */
class BaseCurrencyModel
@Inject
constructor(
    launchManager: MainLaunchManagerInterface,
    private val keyValueStorage: KeyValueStorageFacadeInterface
) : ModelBase(launchManager),
    BaseCurrencyModelInterface {
    /**
     *
     */
    override val startCurrencyValue: Currency = Currency.USD

    /**
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveCurrency(currency: Currency, result: (DisplayingError?) -> Unit) {
        launchManager.launchFromUIWithException(
            action =  {
                keyValueStorage.setDefaultCurrency(currency)
            },
            resultCallback = { ex ->
                result(ex?.let { GeneralError() })
            })
    }
}