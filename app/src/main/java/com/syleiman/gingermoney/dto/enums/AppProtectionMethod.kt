package com.syleiman.gingermoney.dto.enums

/**
 *
 */
enum class AppProtectionMethod {
    WITHOUT_PROTECTION,
    FINGERPRINT,
    MASTER_PASSWORD;

    companion object Create {
        fun from(sourceValue: String): AppProtectionMethod = values().first { it.toString() == sourceValue }
    }
}