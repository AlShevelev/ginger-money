package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model

import com.syleiman.gingermoney.core.storages.key_value.KeyValueStorageFacadeInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.core.works.WorksManagerInterface
import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.displaying_errors.GeneralError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
) : ModelBase(),
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
     * @param resultCall - the argument is null in case of success, otherwise it contains an error to display
     */
    override fun saveProtectionMethod(protectionMethod: AppProtectionMethod, resultCall: (DisplayingError?) -> Unit) {
        launch {
            val operationResult = try {
                withContext(Dispatchers.IO) {
                    keyValueStorage.setAppProtectionMethod(protectionMethod)
                    keyValueStorage.setAppSetupComplete(true)
                    worksManager.startCurrencyRatesUpdates()       // Start to load currency rates periodically
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
}