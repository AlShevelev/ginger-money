package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 */
class BaseCurrencyModel
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface
) : ModelBase(),
    BaseCurrencyModelInterface {
    /**
     *
     */
    override val startCurrencyValue: Currency = Currency.USD

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveCurrency(currency: Currency, resultCall: (DisplayingError?) -> Unit) {
        launch {
            val operationResult = try {
                withContext(Dispatchers.IO) {
                    keyValueStorage.setDefaultCurrency(currency)
                    null
                }
            }
            catch(ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }

            if(isActive) {
                resultCall(operationResult)
            }
        }
    }
}