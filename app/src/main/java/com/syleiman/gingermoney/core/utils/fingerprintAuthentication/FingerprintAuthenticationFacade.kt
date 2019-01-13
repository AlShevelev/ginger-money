package com.syleiman.gingermoney.core.utils.fingerprintAuthentication

import javax.inject.Inject

/**
 *
 */
class FingerprintAuthenticationFacade
@Inject
constructor() : FingerprintAuthenticationFacadeInterface {
    /**
     *
     */
    override val isAuthenticationPossible: Boolean
        get() = true            // note[AS] fake value so far
}