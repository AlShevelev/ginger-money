package com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.syleiman.gingermoney.core.helpers.coroutines.managers.MainLaunchManagerInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManagerInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.FingerprintAuthEventsHandlerInterface
import com.syleiman.gingermoney.core.utils.fingerprint_auth.eventsHandler.events.*
import com.syleiman.gingermoney.ui.common.mvvm.ModelBase
import javax.inject.Inject

/**
 *
 */
@RequiresApi(Build.VERSION_CODES.M)
class FingerprintModel
@Inject
constructor(
    launchManager: MainLaunchManagerInterface,
    private val fingerprintAuthManager: FingerprintAuthManagerInterface
) : ModelBase(launchManager),
    FingerprintModelInterface {

    private lateinit var fingerprintAuthEventsHandler: FingerprintAuthEventsHandlerInterface

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

    /**
     *
     */
    override fun cancelBackgroundOperations() {
        super.cancelBackgroundOperations()
        fingerprintAuthEventsHandler.cancel()
    }
}