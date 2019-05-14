package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model

import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.FingerprintAuthEventHandler
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseInterface

/**
 *
 */
interface FingerprintModelInterface: ModelBaseInterface {
    /**
     * Start an authentication session
     */
    fun startAuth(eventsCallback: FingerprintAuthEventHandler)

    /**
     *
     */
    fun cancelBackgroundOperations() {}
}