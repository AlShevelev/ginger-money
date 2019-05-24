package com.syleiman.gingermoney.core.utils.fingerprint_auth

import android.os.Build
import androidx.annotation.RequiresApi
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.FingerprintAuthEventsHandlerInterface

interface FingerprintAuthManagerInterface {

    val isAuthenticationPossible: Boolean

    /**
     * Returns wrapper to process an authentication
     */
    @RequiresApi(Build.VERSION_CODES.M)
    fun getEventsHandler(): FingerprintAuthEventsHandlerInterface
}