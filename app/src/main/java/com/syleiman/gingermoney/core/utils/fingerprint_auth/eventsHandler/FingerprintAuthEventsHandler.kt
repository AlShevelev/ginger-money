package com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler

import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.FingerprintAuthEventHandler

interface FingerprintAuthEventsHandler {
    /**
     * Start an authentication session
     */
    fun start(eventsCallback: FingerprintAuthEventHandler)

    /**
     * Cancel an authentication session
     */
    fun cancel()
}