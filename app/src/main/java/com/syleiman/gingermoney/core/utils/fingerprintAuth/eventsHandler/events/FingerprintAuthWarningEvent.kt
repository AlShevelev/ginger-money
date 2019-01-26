package com.syleiman.gingermoney.core.utils.fingerprintAuth.eventsHandler.events

/**
 * Some non-fatal error has happened
 */
data class FingerprintAuthWarningEvent(
    val message: String?
) : FingerprintAuthEvent