package com.syleiman.gingermoney.ui.activities.setup.fragments.protection_method.model

import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displaying_errors.DisplayingError
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface ProtectionMethodModelInterface: ModelBaseInterface {
    /**
     *
     */
    val isFingerprintAuthenticationPossible: Boolean

    /**
     *
     */
    val startProtectionMethod: AppProtectionMethod

    /**
     * @return - the argument is null in case of success, otherwise it contains an error to display
     */
    suspend fun saveProtectionMethod(protectionMethod: AppProtectionMethod): DisplayingError?
}