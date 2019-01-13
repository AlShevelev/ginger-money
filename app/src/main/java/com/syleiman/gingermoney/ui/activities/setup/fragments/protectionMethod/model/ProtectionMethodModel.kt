package com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.model

import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.storages.keyValue.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprintAuthentication.FingerprintAuthenticationFacadeInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError
import com.syleiman.gingermoney.ui.common.displayingErrors.GeneralError
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
    fingerprintAuthenticationFacade: FingerprintAuthenticationFacadeInterface
) : ModelBase(launchManager),
    ProtectionMethodModelInterface {
    /**
     *
     */
    override val isFingerprintAuthenticationPossible: Boolean = fingerprintAuthenticationFacade.isAuthenticationPossible

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
            },
            resultCallback = { ex ->
                result(ex?.let { GeneralError() })
            })
    }
}