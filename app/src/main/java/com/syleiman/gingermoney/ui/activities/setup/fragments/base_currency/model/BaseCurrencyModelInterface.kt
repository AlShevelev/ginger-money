package com.syleiman.gingermoney.ui.activities.setup.fragments.base_currency.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface BaseCurrencyModelInterface : ModelBaseInterface {
    /**
     *
     */
    val startCurrencyValue: Currency

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    suspend fun saveCurrency(currency: Currency): DisplayingError?
}