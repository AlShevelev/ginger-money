package com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler

import com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events.FingerprintAuthEventHandler

/**
 *
 */
interface FingerprintAuthEventsHandlerInterface {
    /**
     * Start an authentication session
     */
    fun start(eventsCallback: FingerprintAuthEventHandler)

    /**
     * Cancel an authentication session
     */
    fun cancel()
}