package com.syleiman.gingermoney.ui.activities.login.dependency_injection

import android.os.Build
import com.syleiman.gingermoney.application.dependency_injection.scopes.ActivityScope
import com.syleiman.gingermoney.core.utils.fingerprint_auth.FingerprintAuthManager
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelImpl
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModelFake
import com.syleiman.gingermoney.ui.activities.login.fragments.fingerprint.model.FingerprintModel
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule {
    @Provides
    @ActivityScope
    internal fun provideFingerprintModel(fingerprintAuthManager: FingerprintAuthManager): FingerprintModel =
        if(fingerprintAuthManager.isAuthenticationPossible && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FingerprintModelImpl(fingerprintAuthManager)
        } else {
            FingerprintModelFake()
        }
}