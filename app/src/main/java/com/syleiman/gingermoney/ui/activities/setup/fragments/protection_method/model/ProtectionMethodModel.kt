package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model

import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.core.works.WorksManagerInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import javax.inject.Inject

/**
 *
 */
class ProtectionMethodModel
@Inject
constructor(
    launchManager: MainLaunchManagerInterface,
    private val keyValueStorage: KeyValueStorageFacadeInterface,
    fingerprintAuthManager: FingerprintAuthManagerInterface,
    private val worksManager: WorksManagerInterface
) : ModelBase(launchManager),
    ProtectionMethodModelInterface {
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
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveProtectionMethod(protectionMethod: AppProtectionMethod, result: (DisplayingError?) -> Unit) {
        launchManager.launchFromUIWithException(
            action =  {
                keyValueStorage.setAppProtectionMethod(protectionMethod)
                keyValueStorage.setAppSetupComplete(true)
                worksManager.startCurrencyRatesUpdates()       // Start to load currency rates periodically
            },
            resultCallback = { ex ->
                result(ex?.let { GeneralError() })
            })
    }
}