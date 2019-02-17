package com.syleiman.gingermoney.ui.activities.main.fragments.settings.model

import com.syleiman.gingermoney.core.global_entities.money.Currency
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    override fun getDefaultCurrency(resultCall: (Currency?, DisplayingError?) -> Unit) =
        getValue(resultCall) {
            keyValueStorage.getDefaultCurrency()
        }

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveDefaultCurrency(defaultCurrency: Currency, resultCall: (DisplayingError?) -> Unit) =
        saveValue(resultCall) {
            keyValueStorage.setDefaultCurrency(defaultCurrency)
        }

    /**
     * Returns current value of an app protection method
     */
    override fun getAppProtectionMethod(resultCall: (AppProtectionMethod?, DisplayingError?) -> Unit) =
        getValue(resultCall) {
            keyValueStorage.getAppProtectionMethod()
        }

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveAppProtectionMethod(appProtectionMethod: AppProtectionMethod, resultCall: (DisplayingError?) -> Unit) =
        saveValue(resultCall) {
            keyValueStorage.setAppProtectionMethod(appProtectionMethod)
        }

    /**
     * Returns current value of start day of week
     */
    override fun getStartDayOfWeek(resultCall: (DayOfWeek?, DisplayingError?) -> Unit) =
        getValue(resultCall) {
            keyValueStorage.getStartDayOfWeek()
        }

    /**
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveStartDayOfWeek(startDayOfWeek: DayOfWeek, resultCall: (DisplayingError?) -> Unit) =
        saveValue(resultCall) {
            keyValueStorage.setStartDayOfWeek(startDayOfWeek)
        }

    /**
     *
     */
    override fun getAppProtectionMethods(): List<AppProtectionMethod> =
        if(fingerprintAuthManager.isAuthenticationPossible) {
            listOf(AppProtectionMethod.WITHOUT_PROTECTION, AppProtectionMethod.FINGERPRINT, AppProtectionMethod.MASTER_PASSWORD)
        }
        else {
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

    /**
     *
     */
    private fun saveValue(resultCall: (DisplayingError?) -> Unit, saveAction: () -> Unit) {
        launch {
            val operationResult = try {
                withContext(Dispatchers.IO) {
                    saveAction()
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

    /**
     *
     */
    private fun <T>getValue(resultCall: (T?, DisplayingError?) -> Unit, getAction: () -> T?) {
        launch {
            val operationResult = try {
                withContext(Dispatchers.IO) {
                    getAction()
                }
            }
            catch(ex: Exception) {
                ex.printStackTrace()
                null
            }

            if(isActive) {
                resultCall(operationResult, if(operationResult == null) GeneralError() else null)
            }
        }
    }
}