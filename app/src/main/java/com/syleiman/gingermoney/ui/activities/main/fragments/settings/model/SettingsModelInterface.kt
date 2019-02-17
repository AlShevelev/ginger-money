package com.syleiman.gingermoney.ui.activities.main.fragments.settings.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import org.threeten.bp.DayOfWeek

/**
 *
 */
interface SettingsModelInterface : ModelBaseInterface {
    /**
     * Returns current value of default selecedCurrency
     */
    fun getDefaultCurrency(resultCall: (Currency?, DisplayingError?) -> Unit)

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    fun saveDefaultCurrency(defaultCurrency: Currency, resultCall: (DisplayingError?) -> Unit)

    /**
     * Returns current value of an app protection method
     */
    fun getAppProtectionMethod(resultCall: (AppProtectionMethod?, DisplayingError?) -> Unit)

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    fun saveAppProtectionMethod(appProtectionMethod: AppProtectionMethod, resultCall: (DisplayingError?) -> Unit)

    /**
     * Returns current value of start day of week
     */
    fun getStartDayOfWeek(resultCall: (DayOfWeek?, DisplayingError?) -> Unit)

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    fun saveStartDayOfWeek(startDayOfWeek: DayOfWeek, resultCall: (DisplayingError?) -> Unit)

    /**
     *
     */
    fun getAppProtectionMethods(): List<AppProtectionMethod>

    /**
     *
     */
    fun getAllDaysOfWeek(): List<DayOfWeek>

}