package com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events

/**
 * Some fatal error has happened
 */
data class FingerprintAuthErrorEvent(
    val message: String?
) : FingerprintAuthEvent