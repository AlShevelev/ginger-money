package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model

import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacade
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.core.works.WorksManager
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.mvvm.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import org.threeten.bp.DayOfWeek
import javax.inject.Inject

class ProtectionMethodModelImpl
@Inject
constructor(
    private val keyValueStorage: KeyValueStorageFacade,
    fingerprintAuthManager: FingerprintAuthManager,
    private val worksManager: WorksManager
) : ModelBaseImpl(),
    ProtectionMethodModel {

    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthManager.isAuthenticationPossible

    override val startProtectionMethod: AppProtectionMethod
        get() = AppProtectionMethod.WITHOUT_PROTECTION

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    override suspend fun saveProtectionMethod(protectionMethod: AppProtectionMethod): DisplayingError? =
        saveValue {
            keyValueStorage.setAppProtectionMethod(protectionMethod)
            keyValueStorage.setStartDayOfWeek(DayOfWeek.MONDAY)
            keyValueStorage.setAppSetupComplete(true)

            worksManager.startCurrencyRatesUpdates()      // Started to load selected Currency rates periodically
        }
}