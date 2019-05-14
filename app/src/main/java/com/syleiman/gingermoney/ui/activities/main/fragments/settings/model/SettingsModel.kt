package com.syleiman.gingermoney.ui.activities.main.fragments.settings.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import com.syleiman.gingermoney.ui.common.mvvm.ModelCallResult
import org.threeten.bp.DayOfWeek
import javax.inject.Inject

/**
 *
 */
class SettingsModel
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    private val fingerprintAuthManager: FingerprintAuthManagerInterface
) : ModelBase(),
    SettingsModelInterface {

    /**
     * Returns current value of default selected Currency
     */
    override suspend fun getDefaultCurrency(): ModelCallResult<out Currency> =
        getValue {
            keyValueStorage.getDefaultCurrency()
        }

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveDefaultCurrency(defaultCurrency: Currency): DisplayingError? =
        saveValue {
            keyValueStorage.setDefaultCurrency(defaultCurrency)
        }

    /**
     * Returns current value of an app protection method
     */
    override suspend fun getAppProtectionMethod(): ModelCallResult<out AppProtectionMethod> =
        getValue {
            keyValueStorage.getAppProtectionMethod()
        }

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveAppProtectionMethod(appProtectionMethod: AppProtectionMethod): DisplayingError? =
        saveValue {
            keyValueStorage.setAppProtectionMethod(appProtectionMethod)
        }

    /**
     * Returns current value of start day of week
     */
    override suspend fun getStartDayOfWeek(): ModelCallResult<out DayOfWeek> =
        getValue {
            keyValueStorage.getStartDayOfWeek()
        }

    /**
     * @return - null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveStartDayOfWeek(startDayOfWeek: DayOfWeek): DisplayingError? =
        saveValue {
            keyValueStorage.setStartDayOfWeek(startDayOfWeek)
        }

    /**
     *
     */
    override suspend fun getAppProtectionMethods(): List<AppProtectionMethod> =
        if(fingerprintAuthManager.isAuthenticationPossible) {
            listOf(AppProtectionMethod.WITHOUT_PROTECTION, AppProtectionMethod.FINGERPRINT, AppProtectionMethod.MASTER_PASSWORD)
        } else {
            listOf(AppProtectionMethod.WITHOUT_PROTECTION, AppProtectionMethod.MASTER_PASSWORD)
        }

    /**
     *
     */
    override fun getAllDaysOfWeek(): List<DayOfWeek> =
        listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY)
}