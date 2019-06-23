package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.FingerprintAuthEventsHandler
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.FingerprintAuthEventHandler
import com.syleiman.gingermoney.ui.common.mvvm.ModelBaseImpl
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.M)
class FingerprintModelImpl
@Inject
constructor(
    private val fingerprintAuthManager: FingerprintAuthManager
) : ModelBaseImpl(),
    FingerprintModel {

    private lateinit var fingerprintAuthEventsHandler: FingerprintAuthEventsHandler

    private val eventsCallbacks = mutableSetOf<FingerprintAuthEventHandler>()

    private var isStarted = false

    /**
     * Start an authentication session
     */
    override fun startAuth(eventsCallback: FingerprintAuthEventHandler) {
        eventsCallbacks.add(eventsCallback)

        if(isStarted) {
            return
        }

        fingerprintAuthEventsHandler = fingerprintAuthManager.getEventsHandler()

        fingerprintAuthEventsHandler.start { event ->
            eventsCallbacks.forEach { it(event) }
        }

        isStarted = true
    }

    override fun cancelBackgroundOperations() {
        fingerprintAuthEventsHandler.cancel()
    }
}