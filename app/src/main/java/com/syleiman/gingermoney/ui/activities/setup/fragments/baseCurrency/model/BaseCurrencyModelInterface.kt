package com.syleiman.gingermoney.ui.activities.setup.fragments.baseCurrency.model

import com.syleiman.gingermoney.core.globalEntities.money.Currency
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError
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
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    fun saveCurrency(currency: Currency, result: (DisplayingError?) -> Unit)
}