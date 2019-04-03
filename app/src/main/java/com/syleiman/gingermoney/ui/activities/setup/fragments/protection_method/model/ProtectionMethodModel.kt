package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model

import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.core.works.WorksManagerInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.DayOfWeek
import javax.inject.Inject

/**
 *
 */
class ProtectionMethodModel
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    fingerprintAuthManager: FingerprintAuthManagerInterface,
    private val worksManager: WorksManagerInterface
) : ProtectionMethodModelInterface {
    /**
     *
     */
    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthManager.isAuthenticationPossible

    /**
     *
     */
    override val startProtectionMethod: AppProtectionMethod
        get() = AppProtectionMethod.WITHOUT_PROTECTION

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveProtectionMethod(protectionMethod: AppProtectionMethod): DisplayingError? =
        withContext(Dispatchers.IO) {
            try {
                keyValueStorage.setAppProtectionMethod(protectionMethod)
                keyValueStorage.setStartDayOfWeek(DayOfWeek.MONDAY)
                keyValueStorage.setAppSetupComplete(true)

                worksManager.startCurrencyRatesUpdates()      // Started to load selecedCurrency rates periodically
                null
            } catch (ex: Exception) {
                ex.printStackTrace()
                GeneralError()
            }
        }
}