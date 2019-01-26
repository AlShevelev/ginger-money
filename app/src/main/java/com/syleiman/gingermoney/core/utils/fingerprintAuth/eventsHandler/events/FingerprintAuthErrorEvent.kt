package com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events

/**
 * Some fatal error has happened
 */
data class FingerprintAuthErrorEvent(
    val message: String?
) : FingerprintAuthEvent