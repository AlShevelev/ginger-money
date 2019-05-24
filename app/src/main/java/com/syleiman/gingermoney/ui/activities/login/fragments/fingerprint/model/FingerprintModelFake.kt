package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model

import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.FingerprintAuthEventHandler

/**
 * An empty stub  model in case when fingerprint authentication is impossible
 */
class FingerprintModelFake : FingerprintModelInterface {
    /**
     * Start an authentication session
     */
    override fun startAuth(eventsCallback: FingerprintAuthEventHandler) {
        //  do nothing
    }

    override fun cancelBackgroundOperations() {
        // do nothing
    }
}