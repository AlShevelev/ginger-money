package com.syleiman.gingermoney.ui.activities.main.fragments.settings.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import org.threeten.bp.DayOfWeek

/**
 *
 */
interface SettingsModelInterface: ModelBaseInterface {
    /**
     * Returns current value of default Currency
     */
    suspend fun getDefaultCurrency(): ModelCallResult<out Currency>

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    suspend fun saveDefaultCurrency(defaultCurrency: Currency): DisplayingError?

    /**
     * Returns current value of an app protection method
     */
    suspend fun getAppProtectionMethod(): ModelCallResult<out AppProtectionMethod>

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    suspend fun saveAppProtectionMethod(appProtectionMethod: AppProtectionMethod): DisplayingError?

    /**
     * Returns current value of start day of week
     */
    suspend fun getStartDayOfWeek(): ModelCallResult<out DayOfWeek>

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    suspend fun saveStartDayOfWeek(startDayOfWeek: DayOfWeek): DisplayingError?

    /**
     *
     */
    suspend fun getAppProtectionMethods(): List<AppProtectionMethod>

    /**
     *
     */
    fun getAllDaysOfWeek(): List<DayOfWeek>

}