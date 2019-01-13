package com.syleiman.gingermoney.ui.activities.setup.fragments.protectionMethod.model

import com.syleiman.gingermoney.dto.enums.AppProtectionMethod
import com.syleiman.gingermoney.ui.common.displayingErrors.DisplayingError
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
     * @param result - the argument is null in case of success, otherwise it contains an error to display
     */
    fun saveProtectionMethod(protectionMethod: AppProtectionMethod, result: (DisplayingError?) -> Unit)
}